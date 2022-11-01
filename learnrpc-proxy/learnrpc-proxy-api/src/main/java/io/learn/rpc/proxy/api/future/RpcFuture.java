package io.learn.rpc.proxy.api.future;

import io.learn.rpc.common.threadpool.ClientThreadPool;
import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.request.RpcRequest;
import io.learn.rpc.protocol.response.RpcResponse;
import io.learn.rpc.proxy.api.callback.AsyncRpcCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common.future
 * @className: RpcFuture
 * @author: ycd20
 * @description: future
 * @date: 2022/10/30 22:25
 * @version: 1.0
 */
public class RpcFuture extends CompletableFuture<Object> {
    private static final Logger log = LoggerFactory.getLogger(RpcFuture.class);

    private Sync sync;
    private RpcProtocol<RpcRequest> requestRpcProtocol;
    private RpcProtocol<RpcResponse> responseRpcProtocol;
    private long startTime;

    private long responseTimeThreshold = 5000;

    private List<AsyncRpcCallback> pendingCallbacks = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();

    public RpcFuture(RpcProtocol<RpcRequest> requestRpcProtocol) {
        this.sync = new Sync();
        this.requestRpcProtocol = requestRpcProtocol;
        this.startTime = System.currentTimeMillis();
    }

    private void runCallBack(final AsyncRpcCallback callback) {
        final RpcResponse res = this.responseRpcProtocol.getBody();
        ClientThreadPool.submit(() -> {
            if (!res.isError()) {
                callback.onSuccess(res.getResult());
            } else {
                callback.onException(new RuntimeException("Response error", new Throwable(res.getError())));
            }
        });
    }

    public RpcFuture addCallback(AsyncRpcCallback callback) {
        lock.lock();
        try {
            if (isDone()) {
                runCallBack(callback);
            } else {
                this.pendingCallbacks.add(callback);
            }
        } finally {
            lock.unlock();
        }
        return this;
    }

    private void invokeCallbacks() {
        lock.lock();
        try {
            for (final AsyncRpcCallback callback : pendingCallbacks) {
                runCallBack(callback);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isDone() {
        return sync.isDone();
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        sync.acquire(-1);
        if (this.responseRpcProtocol != null) {
            return this.responseRpcProtocol.getBody().getResult();
        } else {
            return null;
        }
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        boolean success = sync.tryAcquireNanos(-1, unit.toNanos(timeout));
        if (success) {
            if (this.responseRpcProtocol != null) {
                return this.responseRpcProtocol.getBody().getResult();
            } else {
                return null;
            }
        } else {
            throw new RuntimeException("Timeout exception id:" +
                    this.requestRpcProtocol.getHeader().getRequestId()
                    + ".Request ClassName:" +
                    this.requestRpcProtocol.getBody().getClassName()
                    + ".Request Method:" +
                    this.requestRpcProtocol.getBody().getMethodName());
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    public void done(RpcProtocol<RpcResponse> responseRpcProtocol) {
        this.responseRpcProtocol = responseRpcProtocol;
        sync.release(1);
        //call invokeCallbacks
        invokeCallbacks();
        //threshold
        long responseTime = System.currentTimeMillis() - startTime;
        if (responseTime > this.responseTimeThreshold) {
            log.warn("Service response time is too slow .RequestId = "
                    + responseRpcProtocol.getHeader().getRequestId() + ".ResponseTime= " +
                    responseTime + " ms");
        }
    }

    static class Sync extends AbstractQueuedSynchronizer {
        @Serial
        private static final long serialVersionUID = 1L;

        //future status
        private final int done = 1;
        private final int pending = 0;

        @Override
        protected boolean tryAcquire(int acquires) {
            return getState() == done;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == pending) {
                if (compareAndSetState(pending, done)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isDone() {
            getState();
            return getState() == done;
        }
    }
}

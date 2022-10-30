package io.learn.rpc.consumer.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.rpc.consumer.common.context.RpcContext;
import io.learn.rpc.consumer.common.future.RpcFuture;
import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.header.RpcHeader;
import io.learn.rpc.protocol.request.RpcRequest;
import io.learn.rpc.protocol.response.RpcResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common.handler
 * @className: RpcConsumerHandler
 * @author: ycd20
 * @description: rpc consumer handler
 * @date: 2022/10/30 20:52
 * @version: 1.0
 */
public class RpcConsumerHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcResponse>> {
    private final Logger log = LoggerFactory.getLogger(RpcConsumerHandler.class);

    private volatile Channel channel;

    private SocketAddress remotePeer;

    /**
     * save id and protocol map
     */
    private Map<Long, RpcProtocol<RpcResponse>> pendingResponse = new ConcurrentHashMap<>();

    private Map<Long, RpcFuture> pendingRpc = new ConcurrentHashMap<>();

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getRemotePeer() {
        return remotePeer;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remotePeer = this.channel.remoteAddress();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcResponse> protocol) throws Exception {
        if (protocol == null) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        log.info("server consumer receive message===>>>{}", mapper.writeValueAsString(protocol));
        RpcHeader header = protocol.getHeader();
        long requestId = header.getRequestId();
        RpcFuture rpcFuture = pendingRpc.remove(requestId);
        if (rpcFuture != null) {
            rpcFuture.done(protocol);
        }
    }

    public RpcFuture sendRequest(RpcProtocol<RpcRequest> protocol, boolean async, boolean oneway) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        log.info("server consumer send message===>>>{}", mapper.writeValueAsString(protocol));
        return oneway ? this.sendRequestOneway(protocol) : async ?
                sendRequestAsync(protocol) : this.sendRequestSync(protocol);
    }

    private RpcFuture sendRequestSync(RpcProtocol<RpcRequest> protocol) {
        RpcFuture rpcFuture = this.getRpcFuture(protocol);
        channel.writeAndFlush(protocol);
        return rpcFuture;
    }

    private RpcFuture sendRequestAsync(RpcProtocol<RpcRequest> protocol) {
        RpcFuture rpcFuture = this.getRpcFuture(protocol);
        //if async put rpcFuture into context
        RpcContext.getContext().setRpcFuture(rpcFuture);
        channel.writeAndFlush(protocol);
        return null;
    }

    private RpcFuture sendRequestOneway(RpcProtocol<RpcRequest> protocol) {
        channel.writeAndFlush(protocol);
        return null;
    }

    private RpcFuture getRpcFuture(RpcProtocol<RpcRequest> protocol) {
        RpcFuture rpcFuture = new RpcFuture(protocol);
        RpcHeader header = protocol.getHeader();
        long requestId = header.getRequestId();
        pendingRpc.put(requestId, rpcFuture);
        return rpcFuture;
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

}

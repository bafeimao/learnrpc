package io.learn.rpc.consumer.common;

import io.learn.rpc.common.helper.RpcServiceHelper;
import io.learn.rpc.common.threadpool.ClientThreadPool;
import io.learn.rpc.consumer.common.handler.RpcConsumerHandler;
import io.learn.rpc.consumer.common.helper.RpcConsumerHandlerHelper;
import io.learn.rpc.consumer.common.initializer.RpcConsumerInitializer;
import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.meta.ServiceMeta;
import io.learn.rpc.protocol.request.RpcRequest;
import io.learn.rpc.proxy.api.consumer.Consumer;
import io.learn.rpc.proxy.api.future.RpcFuture;
import io.learn.rpc.registry.api.RegistryService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common
 * @className: RpcConsumer
 * @author: ycd20
 * @description: rpc consumer
 * @date: 2022/10/30 21:04
 * @version: 1.0
 */
public class RpcConsumer implements Consumer {
    private final Logger logger = LoggerFactory.getLogger(RpcConsumer.class);

    private final Bootstrap bootstrap;
    private final EventLoopGroup eventLoopGroup;
    private static volatile RpcConsumer instance;

    private static Map<String, RpcConsumerHandler> handlerMap =
            new ConcurrentHashMap<>();

    private RpcConsumer() {
        bootstrap = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup(4);
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new RpcConsumerInitializer());
    }

    public static RpcConsumer getInstance() {
        if (instance == null) {
            synchronized (RpcConsumer.class) {
                if (instance == null) {
                    instance = new RpcConsumer();
                }
            }
        }
        return instance;
    }

    public void close() {
        RpcConsumerHandlerHelper.closeRpcClientHandler();
        eventLoopGroup.shutdownGracefully();
        ClientThreadPool.shutdown();
    }

    @Override
    public RpcFuture sendRequest(RpcProtocol<RpcRequest> protocol, RegistryService registryService) throws Exception {
        RpcRequest request = protocol.getBody();
        String serviceKey = RpcServiceHelper.buildServiceKey(request.getClassName(),
                request.getVersion(), request.getGroup());
        Object[] params = request.getParameters();
        int invokerHashCode = (params == null || params.length <= 0) ?
                serviceKey.hashCode() : params[0].hashCode();
        ServiceMeta serviceMeta = registryService.discovery(serviceKey, invokerHashCode);
        if (serviceMeta != null) {
            RpcConsumerHandler handler = RpcConsumerHandlerHelper.get(serviceMeta);
            //cache doesn't have RpcClientHandler
            if (handler == null) {
                handler = getRpcConsumerHandler(serviceMeta.getServiceAddr(),
                        serviceMeta.getServicePort());
                RpcConsumerHandlerHelper.put(serviceMeta, handler);
            } else if (!handler.getChannel().isActive()) {
                //exist but not active
                handler.close();
                handler = getRpcConsumerHandler(serviceMeta.getServiceAddr(),
                        serviceMeta.getServicePort());
                RpcConsumerHandlerHelper.put(serviceMeta, handler);
            }
            return handler.sendRequest(protocol, request.getAsync(), request.getOneway());
        }
        return null;
    }

    /**
     * create connection and return RpcClientHandler
     *
     * @param serviceAddress
     * @param port
     * @return
     */
    private RpcConsumerHandler getRpcConsumerHandler(String serviceAddress, int port) throws InterruptedException {
        ChannelFuture channelFuture = bootstrap.connect(serviceAddress, port).sync();
        channelFuture.addListener((ChannelFutureListener) listener -> {
            if (channelFuture.isSuccess()) {
                logger.info("connect rpc server {} on port {} success", serviceAddress, port);
            } else {
                logger.error("connect rpc server{} on port {} failed", serviceAddress, port);
                channelFuture.cause().printStackTrace();
                eventLoopGroup.shutdownGracefully();
            }
        });
        return channelFuture.channel().pipeline().get(RpcConsumerHandler.class);
    }

}

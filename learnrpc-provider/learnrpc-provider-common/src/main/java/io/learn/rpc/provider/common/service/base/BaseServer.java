package io.learn.rpc.provider.common.service.base;

import io.learn.rpc.codec.RpcDecoder;
import io.learn.rpc.codec.RpcEncoder;
import io.learn.rpc.provider.common.handler.RpcProviderHandler;
import io.learn.rpc.provider.common.api.Server;
import io.learn.rpc.registry.api.RegistryService;
import io.learn.rpc.registry.api.config.RegistryConfig;
import io.learn.rpc.registry.zookeeper.ZookeeperRegistryService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.provider.common.service.base
 * @className: BaseServer
 * @author: ycd20
 * @description: Server implements
 * @date: 2022/10/30 10:14
 * @version: 1.0
 */
public class BaseServer implements Server {
    protected RegistryService registryService;

    private final Logger log = LoggerFactory.getLogger(BaseServer.class);

    /**
     * server host or ipaddress
     */
    protected String host = "127.0.0.1";
    /**
     * host
     */
    protected int port = 27110;

    /**
     * save entity relation
     */
    protected Map<String, Object> handlerMap = new HashMap<>();
    /**
     * reflect type
     */
    private String reflectType;

    public BaseServer(String serverAddress, String registryAddress, String registryType, String reflectType) {
        if (!StringUtils.isEmpty(serverAddress)) {
            String[] serverArray = serverAddress.split(":");
            this.host = serverArray[0];
            this.port = Integer.parseInt(serverArray[1]);
        }
        this.reflectType = reflectType;
        this.registryService = this.getRegistryService(registryAddress, registryType);
    }

    private RegistryService getRegistryService(String registryAddress, String registryType) {
        RegistryService registryService = null;
        try {
            registryService = new ZookeeperRegistryService();
            registryService.init(new RegistryConfig(registryAddress, registryType));
        } catch (Exception e) {
            log.error("RpcServer init error");
        }
        return registryService;
    }


    @Override
    public void startNettyServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    //todo self define protocol
                                    .addLast(new RpcDecoder())
                                    .addLast(new RpcEncoder())
                                    .addLast(new RpcProviderHandler(reflectType, handlerMap));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(host, port).sync();
            log.info("Server started on {}:{}", host, port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("RPC SERVER start error", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

package io.learn.rpc.test.consumer.codec.init;

import io.learn.rpc.codec.RpcDecoder;
import io.learn.rpc.codec.RpcEncoder;
import io.learn.rpc.test.consumer.codec.handler.RpcTestConsumerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.consumer.codec.init
 * @className: RpcTestConsumerInitializer
 * @author: ycd20
 * @description: TODO
 * @date: 2022/10/30 18:24
 * @version: 1.0
 */
public class RpcTestConsumerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline cp = ch.pipeline();
        cp.addLast(new RpcEncoder());
        cp.addLast(new RpcDecoder());
        cp.addLast(new RpcTestConsumerHandler());
    }
}

package io.learn.rpc.consumer.common.initializer;

import io.learn.rpc.codec.RpcDecoder;
import io.learn.rpc.codec.RpcEncoder;
import io.learn.rpc.consumer.common.handler.RpcConsumerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common.initializer
 * @className: RpcConsumerInitializer
 * @author: ycd20
 * @description: initializer
 * @date: 2022/10/30 21:02
 * @version: 1.0
 */
public class RpcConsumerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline cp = ch.pipeline();
        cp.addLast(new RpcEncoder());
        cp.addLast(new RpcDecoder());
        cp.addLast(new RpcConsumerHandler());
    }
}

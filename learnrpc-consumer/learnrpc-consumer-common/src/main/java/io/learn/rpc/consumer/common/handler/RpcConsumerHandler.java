package io.learn.rpc.consumer.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.rpc.protocol.RpcProtocol;
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

/**
 * @projectName: rpc
 * @package: io.learn.rpc.consumer.common.handler
 * @className: RpcConsumerHandler
 * @author: ycd20
 * @description: rpc consumer handler
 * @date: 2022/10/30 20:52
 * @version: 1.0
 */
public class RpcConsumerHandler extends SimpleChannelInboundHandler<RpcResponse> {
    private final Logger log = LoggerFactory.getLogger(RpcConsumerHandler.class);

    private volatile Channel channel;

    private SocketAddress remotePeer;

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
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        log.info("server consumer receive message===>>>{}", mapper.writeValueAsString(msg));
    }

    public void sendRequest(RpcProtocol<RpcRequest> protocol) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        log.info("server consumer send message===>>>{}", mapper.writeValueAsString(protocol));
        channel.writeAndFlush(protocol);
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

}

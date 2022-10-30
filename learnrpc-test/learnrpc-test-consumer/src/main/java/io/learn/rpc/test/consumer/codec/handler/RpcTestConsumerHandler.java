package io.learn.rpc.test.consumer.codec.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.header.RpcHeaderFactory;
import io.learn.rpc.protocol.request.RpcRequest;
import io.learn.rpc.protocol.response.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.consumer.codec.handler
 * @className: RpcTestConsumerHandler
 * @author: ycd20
 * @description: test consumer
 * @date: 2022/10/30 18:15
 * @version: 1.0
 */
public class RpcTestConsumerHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcResponse>> {

    private final Logger log = LoggerFactory.getLogger(RpcTestConsumerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("send message start...");
        //mock send message
        RpcProtocol<RpcRequest> protocol = new RpcProtocol<>();
        protocol.setHeader(RpcHeaderFactory.getRequestHeader("jdk"));
        RpcRequest request = new RpcRequest();
        request.setClassName("io.learn.rpc.test.DemoService");
        request.setGroup("learn");
        request.setMethodName("hello");
        request.setParameters(new Object[]{"learn"});
        request.setParameterTypes(new Class[]{String.class});
        request.setVersion("1.0.0");
        request.setAsync(false);
        request.setOneway(false);
        protocol.setBody(request);
        ObjectMapper mapper = new ObjectMapper();
        log.info("service consumer send message===>>>{}", mapper.writeValueAsString(protocol));
        ctx.writeAndFlush(protocol);
        log.info("send message complete...");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcResponse> msg) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        log.info("service consumer receive message===>>>{}", mapper.writeValueAsString(msg));
    }
}

package io.learn.rpc.provider.common.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.provider.common.handler
 * @className: RpcProviderHandler
 * @author: ycd20
 * @description: extends netty.channel.SimpleChannelInboundHandler
 * @date: 2022/10/30 9:53
 * @version: 1.0
 */
public class RpcProviderHandler extends SimpleChannelInboundHandler<Object> {
    private final Logger log = LoggerFactory.getLogger(RpcProviderHandler.class);

    private final Map<String, Object> handlerMap;

    public RpcProviderHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("rpc provider received data is===>>>" + msg.toString());
        log.info("handlerMap save data:");
        for (Map.Entry<String, Object> entry : handlerMap.entrySet()) {
            log.info(entry.getKey() + "===" + entry.getValue());
        }
        //return data
        ctx.writeAndFlush(msg);
    }
}

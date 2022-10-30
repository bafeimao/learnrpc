package io.learn.rpc.provider.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.enumeration.RpcType;
import io.learn.rpc.protocol.header.RpcHeader;
import io.learn.rpc.protocol.request.RpcRequest;
import io.learn.rpc.protocol.response.RpcResponse;
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
public class RpcProviderHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcRequest>> {
    private final Logger log = LoggerFactory.getLogger(RpcProviderHandler.class);

    private final Map<String, Object> handlerMap;

    public RpcProviderHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcRequest> protocol) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        log.info("rpc provider received data is===>>>" + mapper.writeValueAsString(protocol));
        log.info("handlerMap save data:");
        for (Map.Entry<String, Object> entry : handlerMap.entrySet()) {
            log.info(entry.getKey() + "===" + entry.getValue());
        }
        RpcHeader header = protocol.getHeader();
        RpcRequest request = protocol.getBody();
        //set header message type to response type
        header.setMsgType((byte) RpcType.RESPONSE.getType());
        //construct response protocol data
        RpcProtocol<RpcResponse> responseRpcProtocol = new RpcProtocol<RpcResponse>();
        RpcResponse response = new RpcResponse();
        response.setResult("data exchange successful");
        response.setAsync(request.getAsync());
        response.setOneway(request.getOneway());
        responseRpcProtocol.setHeader(header);
        responseRpcProtocol.setBody(response);
        //return data
        ctx.writeAndFlush(responseRpcProtocol);
    }
}

package io.learn.rpc.provider.common.handler;

import io.learn.rpc.common.helper.RpcServiceHelper;
import io.learn.rpc.common.threadpool.ServerThreadPool;
import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.enumeration.RpcStatus;
import io.learn.rpc.protocol.enumeration.RpcType;
import io.learn.rpc.protocol.header.RpcHeader;
import io.learn.rpc.protocol.request.RpcRequest;
import io.learn.rpc.protocol.response.RpcResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
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
        ServerThreadPool.submit(() -> {
            RpcHeader header = protocol.getHeader();
            RpcRequest request = protocol.getBody();
            log.debug("Receive request" + header.getRequestId());
            //set header message type to response type
            header.setMsgType((byte) RpcType.RESPONSE.getType());
            //construct response protocol data
            RpcProtocol<RpcResponse> responseRpcProtocol = new RpcProtocol<RpcResponse>();
            RpcResponse response = new RpcResponse();
            try {
                Object result = handle(request);
                response.setResult(result);
                response.setAsync(request.getAsync());
                response.setOneway(request.getOneway());
                header.setStatus((byte) RpcStatus.SUCCESS.getCode());
            } catch (Throwable t) {
                response.setError(t.toString());
                header.setStatus((byte) RpcStatus.FAIL.getCode());
                log.error("RPC Server handle request error", t);
            }
            responseRpcProtocol.setHeader(header);
            responseRpcProtocol.setBody(response);
            //return data
            ctx.writeAndFlush(responseRpcProtocol).addListener(
                    (ChannelFutureListener) future -> log.debug("Send response for request " + header.getRequestId()));
        });

    }

    private Object handle(RpcRequest request) throws Throwable {
        String serviceKey =
                RpcServiceHelper.buildServiceKey(request.getClassName(),
                        request.getVersion(), request.getGroup());
        Object serviceBean = handlerMap.get(serviceKey);
        if (serviceBean == null) {
            throw new RuntimeException(String.format("service not exist:%s:%s",
                    request.getClassName(), request.getMethodName()));
        }
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        log.debug(serviceClass.getName());
        log.debug(methodName);

        if (parameters != null && parameters.length > 0) {
            for (Object parameter : parameters) {
                log.debug(parameter.toString());
            }
        }
        return invokeMethod(serviceBean, serviceClass, methodName, parameterTypes, parameters);
    }

    private Object invokeMethod(Object serviceBean, Class<?> serviceClass,
                                String methodName, Class<?>[] parameterTypes, Object[] parameters) throws Throwable {
        Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);
    }


}

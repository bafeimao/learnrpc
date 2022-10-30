package io.learn.rpc.test.consumer.handler;

import io.learn.rpc.consumer.common.RpcConsumer;
import io.learn.rpc.consumer.common.future.RpcFuture;
import io.learn.rpc.consumer.common.handler.RpcConsumerHandler;
import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.header.RpcHeaderFactory;
import io.learn.rpc.protocol.request.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.consumer.handler
 * @className: RpcConsumerHandlerTest
 * @author: ycd20
 * @description: consumer test
 * @date: 2022/10/30 21:42
 * @version: 1.0
 */
public class RpcConsumerHandlerTest {
    private static final Logger log = LoggerFactory.getLogger(RpcConsumerHandler.class);

    public static void main(String[] args) throws Exception {
        RpcConsumer consumer = RpcConsumer.getInstance();
        RpcFuture future = consumer.sendRequest(getRpcRequestProtocol());
        log.info("get data from server consumer===>>>" + future.get());
        consumer.close();
    }

    private static RpcProtocol<RpcRequest> getRpcRequestProtocol() {
        //mock
        RpcProtocol<RpcRequest> protocol = new RpcProtocol<>();
        protocol.setHeader(RpcHeaderFactory.getRequestHeader("jdk"));
        RpcRequest request = new RpcRequest();
        request.setClassName("io.learn.rpc.test.api.DemoService");
        request.setGroup("learn");
        request.setMethodName("hello");
        request.setParameters(new Object[]{"learn"});
        request.setParameterTypes(new Class[]{String.class});
        request.setVersion("1.0.0");
        request.setAsync(false);
        request.setOneway(false);
        protocol.setBody(request);
        return protocol;
    }
}

package io.learn.rpc.test.consumer;

import io.learn.rpc.consumer.RpcClient;
import io.learn.rpc.test.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.consumer
 * @className: RpcConsumerNativeTest
 * @author: ycd20
 * @description: rpcConsumerNative test
 * @date: 2022/10/31 22:15
 * @version: 1.0
 */
public class RpcConsumerNativeTest {
    private static final Logger log = LoggerFactory.getLogger(RpcConsumerNativeTest.class);

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient("1.0.0", "learn", "jdk", 3000, false, false);
        DemoService demoService = rpcClient.create(DemoService.class);
        String result = demoService.hello("learn");
        log.info("return result data===>>>" + result);
        rpcClient.shutdown();
    }
}

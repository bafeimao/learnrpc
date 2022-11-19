package consumer;

import io.learn.rpc.consumer.RpcClient;
import io.learn.rpc.proxy.api.async.IAsyncObjectProxy;
import io.learn.rpc.proxy.api.future.RpcFuture;
import io.learn.rpc.test.api.DemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private RpcClient rpcClient;

    @BeforeEach
    public void initRpcClient() {
        rpcClient = new RpcClient("127.0.0.1:2181", "zookeeper",
                "1.0.0", "learn", "jdk", 3000, false, false);
    }

    private static final Logger log = LoggerFactory.getLogger(RpcConsumerNativeTest.class);

    @Test
    public void testInterfaceRpc() {
        DemoService demoService = rpcClient.create(DemoService.class);
        String result = demoService.hello("learn");
        log.info("return result" + result);
        rpcClient.shutdown();
    }
//    /**
//     * todo 神奇的bug
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        RpcClient rpcClient = new RpcClient("1.0.0", "learn", "jdk", 3000, false, false);
//        DemoService demoService = rpcClient.create(DemoService.class);
//        String result = demoService.hello("learn");
//        log.info("return result data===>>>" + result);
//        rpcClient.shutdown();
//    }
//
//    @Test
//    public void testAsyncInterfaceRpc() throws Exception {
//        RpcClient rpcClient = new RpcClient("1.0.0", "learn", "jdk", 3000, false, false);
//        IAsyncObjectProxy demoService =
//                rpcClient.createAsync(DemoService.class);
//        RpcFuture future = demoService.call("hello", "learn");
//        log.info("return data is===>>>" + future.get());
//        rpcClient.shutdown();
//    }


}

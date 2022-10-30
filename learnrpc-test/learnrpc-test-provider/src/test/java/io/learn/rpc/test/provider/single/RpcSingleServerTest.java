package io.learn.rpc.test.provider.single;

import io.learn.rpc.provider.RpcSingleServer;
import org.junit.jupiter.api.Test;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.provider.service
 * @className: RpcSingleServerTest
 * @author: ycd20
 * @description: singleServerTest
 * @date: 2022/10/30 11:04
 * @version: 1.0
 */
public class RpcSingleServerTest {
    @Test
    public void startRpcSingleServer() {
        RpcSingleServer singleServer =
                new RpcSingleServer("127.0.0.1:27880", "io.learn.rpc.test", "jdk");
        singleServer.startNettyServer();
    }
}

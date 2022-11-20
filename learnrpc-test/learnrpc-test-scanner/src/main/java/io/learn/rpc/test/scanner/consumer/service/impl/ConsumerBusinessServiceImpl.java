package io.learn.rpc.test.scanner.consumer.service.impl;

import io.learn.rpc.spi.annotation.RpcReference;
import io.learn.rpc.test.scanner.consumer.service.ConsumerBusinessService;
import io.learn.rpc.test.scanner.service.DemoService;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.scanner.consumer.service.impl
 * @className: ConsumerBusinessServiceImpl
 * @author: ycd20
 * @description: consumer business service impl
 * @date: 2022/10/29 21:43
 * @version: 1.0
 */
public class ConsumerBusinessServiceImpl implements ConsumerBusinessService {

    @RpcReference(registryType = "zookeeper", registryAddress = "127.0.0.1:2181", version = "1.0.0", group = "learn")
    private DemoService demoService;
}

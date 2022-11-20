package io.learn.rpc.test.spi.service.impl;

import io.learn.rpc.spi.annotation.SPIClass;
import io.learn.rpc.test.spi.service.SPIService;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.spi.service.impl
 * @className: SPIServiceImpl
 * @author: ycd20
 * @description: impl
 * @date: 2022/11/20 11:37
 * @version: 1.0
 */
@SPIClass
public class SPIServiceImpl implements SPIService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}

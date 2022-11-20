package io.learn.rpc.test.spi.service;

import io.learn.rpc.spi.annotation.SPI;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.spi.service
 * @className: SPIService
 * @author: ycd20
 * @description: spi service
 * @date: 2022/11/20 11:36
 * @version: 1.0
 */
@SPI("spiService")
public interface SPIService {
    String hello(String name);
}

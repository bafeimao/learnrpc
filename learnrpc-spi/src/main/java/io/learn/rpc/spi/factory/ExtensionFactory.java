package io.learn.rpc.spi.factory;

import io.learn.rpc.spi.annotation.SPI;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.api.factory
 * @className: ExtensionFactory
 * @author: ycd20
 * @description: extension factory
 * @date: 2022/11/20 10:30
 * @version: 1.0
 */
@SPI("spi")
public interface ExtensionFactory {

    /**
     * get extension object
     *
     * @param key   key
     * @param clazz class object type
     * @param <T>   generic type
     * @return extension object
     */
    <T> T getExtension(String key, Class<T> clazz);
}

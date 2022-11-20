package io.learn.rpc.spi.factory;

import io.learn.rpc.spi.annotation.SPI;
import io.learn.rpc.spi.annotation.SPIClass;
import io.learn.rpc.spi.loader.ExtensionLoader;

import java.util.Optional;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.api.factory
 * @className: SPIExtensionFactory
 * @author: ycd20
 * @description: extensionFactory
 * @date: 2022/11/20 10:31
 * @version: 1.0
 */
@SPIClass
public class SPIExtensionFactory implements ExtensionFactory {

    @Override
    public <T> T getExtension(String key, Class<T> clazz) {
        return Optional.ofNullable(clazz)
                .filter(Class::isInterface)
                .filter(cls -> cls.isAnnotationPresent(SPI.class))
                .map(ExtensionLoader::getExtensionLoader)
                .map(ExtensionLoader::getDefaultSpiClassInstance)
                .orElse(null);
    }

}

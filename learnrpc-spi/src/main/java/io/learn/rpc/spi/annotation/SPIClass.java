package io.learn.rpc.spi.annotation;

import java.lang.annotation.*;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.annotation
 * @className: SPIClass
 * @author: ycd20
 * @description: spi class
 * @date: 2022/11/20 10:28
 * @version: 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPIClass {
}

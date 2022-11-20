package io.learn.rpc.spi.annotation;

import java.lang.annotation.*;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.annotation
 * @className: SPI
 * @author: ycd20
 * @description: SPI
 * @date: 2022/11/20 10:27
 * @version: 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {
    String value() default "";
}

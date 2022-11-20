package io.learn.rpc.spi.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.annotation
 * @className: RpcService
 * @author: ycd20
 * @description: rpc service provider
 * @date: 2022/10/20 22:22
 * @version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {
    /**
     * interface class
     */
    Class<?> interfaceClass() default void.class;

    /**
     * interface class name
     */
    String interfaceClassName() default "";

    /**
     * version
     */
    String version() default "1.0.0";

    /**
     * service group default is null
     */
    String group() default "";

}

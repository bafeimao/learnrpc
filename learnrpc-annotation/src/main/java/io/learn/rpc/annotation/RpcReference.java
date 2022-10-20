package io.learn.rpc.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.annotation
 * @className: RpcReference
 * @author: ycd20
 * @description: rpc consumer
 * @date: 2022/10/20 23:05
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Autowired
public @interface RpcReference {

    /**
     * version
     */
    String version() default "1.0.0";

    /**
     * registry type include: zookeeper,nacos,etcd,consul
     */
    String registryType() default "zookeeper";

    /**
     * registry address
     */
    String registryAddress() default "127.0.0.1:2181";

    /**
     * loadBalance type include: protostuff,kryo,json,jdk,hessian2,fst
     */

    String serializationType() default "protostuff";

    /**
     * timeout default 5s
     */
    long timeout() default 5000;

    /**
     * isAsync
     */
    boolean async() default false;

    /**
     * oneway
     */
    boolean oneway() default false;

    /**
     * proxyType: jdk proxy,javassist proxy, cglib proxy
     */
    String proxy() default "jdk";

    /**
     * service group
     */
    String group() default "";


}

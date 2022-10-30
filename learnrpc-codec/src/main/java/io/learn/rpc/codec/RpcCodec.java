package io.learn.rpc.codec;

import io.learn.rpc.serialization.api.Serialization;
import io.learn.rpc.serialization.jdk.JdkSerialization;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.codec
 * @className: RpcCodec
 * @author: ycd20
 * @description: get jdk serialization
 * @date: 2022/10/30 16:59
 * @version: 1.0
 */
public interface RpcCodec {
    /**
     * get jdk serialization
     *
     * @return
     */
    default Serialization getJdkSerialization() {
        return new JdkSerialization();
    }
}

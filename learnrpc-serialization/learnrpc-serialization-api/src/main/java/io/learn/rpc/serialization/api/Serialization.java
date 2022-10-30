package io.learn.rpc.serialization.api;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.serialization.api
 * @className: Serialization
 * @author: ycd20
 * @description: serialization
 * @date: 2022/10/30 16:39
 * @version: 1.0
 */
public interface Serialization {
    /**
     * serialization
     *
     * @param obj obj
     * @return serialize
     */
    <T> byte[] serialize(T obj);

    /**
     * deserialize
     *
     * @param data
     * @param cls
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> cls);

}

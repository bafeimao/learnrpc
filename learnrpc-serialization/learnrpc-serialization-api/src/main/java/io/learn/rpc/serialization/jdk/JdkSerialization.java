package io.learn.rpc.serialization.jdk;

import io.learn.rpc.common.exception.SerializeException;
import io.learn.rpc.serialization.api.Serialization;

import java.io.*;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.serialization.jdk
 * @className: JdkSerialization
 * @author: ycd20
 * @description: jdk serialization
 * @date: 2022/10/30 16:51
 * @version: 1.0
 */
public class JdkSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T obj) {
        if (obj == null) {
            throw new SerializeException("serialization object is null");
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(obj);
            return os.toByteArray();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data, Class<T> cls) {
        if (data == null) {
            throw new SerializeException("deserialization data is null");
        }
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            ObjectInputStream in = new ObjectInputStream(is);
            return (T) in.readObject();
        } catch (Exception e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }
}

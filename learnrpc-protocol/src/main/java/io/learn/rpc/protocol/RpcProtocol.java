package io.learn.rpc.protocol;

import io.learn.rpc.protocol.header.RpcHeader;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol
 * @className: RpcProtocol
 * @author: ycd20
 * @description: rpc protocol
 * @date: 2022/10/30 12:26
 * @version: 1.0
 */
public class RpcProtocol<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 35467981123123L;

    /**
     * header
     */
    private RpcHeader header;

    /**
     * message body
     */
    private T body;

    public RpcHeader getHeader() {
        return header;
    }

    public void setHeader(RpcHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}

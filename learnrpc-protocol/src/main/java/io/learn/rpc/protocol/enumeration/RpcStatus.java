package io.learn.rpc.protocol.enumeration;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.enumeration
 * @className: RpcStatus
 * @author: ycd20
 * @description: RpcStatus
 * @date: 2022/10/30 19:19
 * @version: 1.0
 */
public enum RpcStatus {
    /**
     * success
     */
    SUCCESS(0),
    /**
     * fail
     */
    FAIL(1);
    private final int code;

    RpcStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

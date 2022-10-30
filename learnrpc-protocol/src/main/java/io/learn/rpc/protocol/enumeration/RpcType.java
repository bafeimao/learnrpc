package io.learn.rpc.protocol.enumeration;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.enumeration
 * @className: RpcType
 * @author: ycd20
 * @description: RpcType
 * @date: 2022/10/30 11:20
 * @version: 1.0
 */
public enum RpcType {
    /**
     * request message
     */
    REQUEST(1),
    /**
     * response message
     */
    RESPONSE(2),
    /**
     * heart data
     */
    HEARTBEAT(3);

    private final int type;

    RpcType(int type) {
        this.type = type;
    }

    public static RpcType findByType(int type) {
        for (RpcType rpcType : RpcType.values()) {
            if (rpcType.getType() == type) {
                return rpcType;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }


}

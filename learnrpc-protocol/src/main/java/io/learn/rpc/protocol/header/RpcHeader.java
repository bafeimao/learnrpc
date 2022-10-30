package io.learn.rpc.protocol.header;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.header
 * @className: RpcHeader
 * @author: ycd20
 * @description: header
 * @date: 2022/10/30 11:31
 * @version: 1.0
 */
public class RpcHeader implements Serializable {
    @Serial
    private static final long serialVersionUID = 60117894L;

    /*
    +-------------------------------------------------------+
    |魔数 2byte | 报文类型 1byte | 状态 1byte | 消息 ID 8byte|
    +-------------------------------------------------------+
    |       序列化类型 16byte     |      数据长度   4byte    |
    +-------------------------------------------------------+
     */
    /**
     * magicNumber
     */
    private short magic;

    /**
     * message type
     */
    private byte msgType;

    /**
     * status
     */
    private byte status;

    /**
     * message Id 8byte
     */
    private long requestId;

    /**
     * serial 16 byte
     */
    private String serializationType;

    /**
     * message length
     */
    private int msgLen;

    public short getMagic() {
        return magic;
    }

    public void setMagic(short magic) {
        this.magic = magic;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getSerializationType() {
        return serializationType;
    }

    public void setSerializationType(String serializationType) {
        this.serializationType = serializationType;
    }

    public int getMsgLen() {
        return msgLen;
    }

    public void setMsgLen(int msgLen) {
        this.msgLen = msgLen;
    }
}

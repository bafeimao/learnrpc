package io.learn.rpc.protocol.header;

import io.learn.rpc.common.id.IdFactory;
import io.learn.rpc.constants.RpcConstants;
import io.learn.rpc.protocol.enumeration.RpcType;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.header
 * @className: RpcHeaderFactory
 * @author: ycd20
 * @description: head factory
 * @date: 2022/10/30 12:23
 * @version: 1.0
 */
public class RpcHeaderFactory {
    public static RpcHeader getRequestHeader(String serializationType) {
        RpcHeader header = new RpcHeader();
        long requestId = IdFactory.getId();
        header.setMagic(RpcConstants.MAGIC);
        header.setRequestId(requestId);
        header.setMsgType((byte) RpcType.REQUEST.getType());
        header.setStatus((byte) 0X1);
        header.setSerializationType(serializationType);
        return header;
    }
}

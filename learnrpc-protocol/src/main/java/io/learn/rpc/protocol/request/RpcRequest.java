package io.learn.rpc.protocol.request;

import io.learn.rpc.protocol.base.RpcMessage;

import java.io.Serial;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.protocol.request
 * @className: RpcRequest
 * @author: ycd20
 * @description: RpcRequest
 * @date: 2022/10/30 11:25
 * @version: 1.0
 */
public class RpcRequest extends RpcMessage {
    @Serial
    private static final long serialVersionUID = 5555776886650396129L;
    /**
     * class name
     */
    private String className;
    /**
     * methodName
     */
    private String methodName;
    /**
     * parameters type array
     */
    private Class<?>[] parameterTypes;
    /**
     * parameters array
     */
    private Object[] parameters;
    /**
     * version
     */
    private String version;
    /**
     * group
     */
    private String group;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

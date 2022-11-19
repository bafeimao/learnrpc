package io.learn.rpc.common.exception;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.exception
 * @className: RegistryException
 * @author: ycd20
 * @description: registryException
 * @date: 2022/11/19 21:08
 * @version: 1.0
 */
public class RegistryException extends RuntimeException {
    private static final long serialVersionUID = -4827429487234L;

    public RegistryException(final String message) {
        super(message);
    }

    public RegistryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RegistryException(final Throwable cause) {
        super(cause);
    }
}

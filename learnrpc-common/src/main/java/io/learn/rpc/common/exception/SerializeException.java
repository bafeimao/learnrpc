package io.learn.rpc.common.exception;


/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.exception
 * @className: SerializationException
 * @author: ycd20
 * @description: serialization exception
 * @date: 2022/10/30 16:28
 * @version: 1.0
 */
public class SerializeException extends RuntimeException {
    private static final long serialVersionUID = 6745845L;

    public SerializeException(final String message) {
        super(message);
    }

    public SerializeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SerializeException(final Throwable cause) {
        super(cause);
    }
}

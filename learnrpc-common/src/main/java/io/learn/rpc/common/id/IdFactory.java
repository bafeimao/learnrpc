package io.learn.rpc.common.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.id
 * @className: IdFactory
 * @author: ycd20
 * @description: simple id factory
 * @date: 2022/10/30 16:13
 * @version: 1.0
 */
public class IdFactory {
    private final static AtomicLong REQUEST_ID_GEN = new AtomicLong(0);

    public static Long getId() {
        return REQUEST_ID_GEN.incrementAndGet();
    }
}

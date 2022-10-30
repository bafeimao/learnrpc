package io.learn.rpc.constants;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.constants
 * @className: RpcConstants
 * @author: ycd20
 * @description: rpc constants
 * @date: 2022/10/30 12:30
 * @version: 1.0
 */
public class RpcConstants {
    /**
     * message header 32 byte
     */
    public static final int HEADER_TOTAL_LEN = 32;

    /**
     * magic number
     */
    public static final short MAGIC = 0x10;

    /**
     * version
     */
    public static final byte VERSION = 0x1;

    /**
     * REFLECT_TYPE_JDK
     */
    public static final String REFLECT_TYPE_JDK = "jdk";

    /**
     * javassist dynamic proxy
     */
    public static final String PROXY_JAVASSIST = "javassist";

    /**
     * cglib dynamic proxy
     */
    public static final String PROXY_CGLIB = "cglib";

    /**
     * init method
     */
    public static final String INIT_METHOD_NAME = "init";

    /**
     * zookeeper
     */
    public static final String REGISTRY_CENTER_ZOOKEEPER = "zookeeper";

    /**
     * nacos
     */
    public static final String REGISTRY_CENTER_NACOS = "nacos";

    /**
     * apoll
     */
    public static final String REGISTRY_CENTER_APOLL = "apoll";

    /**
     * etcd
     */
    public static final String REGISTRY_CENTER_ETCD = "etcd";

    /**
     * eureka
     */
    public static final String REGISTRY_CENTER_EUREKA = "eureka";

    /**
     * protostuff serialization
     */
    public static final String SERIALIZATION_PROTOSTUFF = "protostuff";

    /**
     * FST serialization
     */
    public static final String SERIALIZATION_FST = "fst";

    /**
     * hessian 2 serialization
     */
    public static final String SERIALIZATION_HESSIAN2 = "hessian2";

    /**
     * jdk serialization
     */
    public static final String SERIALIZATION_JDK = "jdk";

    /**
     * json serialization
     */
    public static final String SERIALIZATION_JSON = "json";

    /**
     * kryo serialization
     */
    public static final String SERIALIZATION_KRYO = "kryo";

    /**
     * server load balance base on ZK
     */
    public static final String SERVER_LOAD_BALANCER_ZKCONSISTENTHASH = "zkconsistenthash";

    public static void main(String[] args) {
        String str = "test00000000000";
        System.out.println(str.replace("0", ""));
    }
}

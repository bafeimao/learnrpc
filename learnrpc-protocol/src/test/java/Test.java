import io.learn.rpc.protocol.RpcProtocol;
import io.learn.rpc.protocol.header.RpcHeader;
import io.learn.rpc.protocol.header.RpcHeaderFactory;
import io.learn.rpc.protocol.request.RpcRequest;

/**
 * @projectName: rpc
 * @package: PACKAGE_NAME
 * @className: Test
 * @author: ycd20
 * @description: test
 * @date: 2022/10/30 16:17
 * @version: 1.0
 */
public class Test {

    public static RpcProtocol<RpcRequest> getRpcProtocol() {
        RpcHeader header = RpcHeaderFactory.getRequestHeader("jdk");
        RpcRequest body = new RpcRequest();
        body.setOneway(false);
        body.setAsync(false);
        body.setClassName("io.learn.rpc.demo.RpcProtocol");
        body.setMethodName("hello");
        body.setGroup("learn");
        body.setParameters(new Object[]{"learn"});
        body.setParameterTypes(new Class[]{String.class});
        body.setVersion("1.0.0");
        RpcProtocol<RpcRequest> rpcProtocol = new RpcProtocol<>();
        rpcProtocol.setBody(body);
        rpcProtocol.setHeader(header);
        return rpcProtocol;
    }
}

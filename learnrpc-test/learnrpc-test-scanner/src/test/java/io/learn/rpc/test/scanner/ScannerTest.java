package io.learn.rpc.test.scanner;

import io.learn.rpc.common.scanner.ClassScanner;
import io.learn.rpc.common.scanner.reference.RpcReferenceScanner;
import io.learn.rpc.provider.common.scanner.RpcServiceScanner;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.scanner.service
 * @className: ScannerTest
 * @author: ycd20
 * @description: scanner test
 * @date: 2022/10/29 21:44
 * @version: 1.0
 */

public class ScannerTest {

    /**
     * scan io.learn.rpc.test.scanner all class
     */
    @Test
    public void testScannerClassNameList() throws Exception {
        List<String> classNameList = ClassScanner.getClassNameList("io.learn.rpc.test.scanner");
        classNameList.forEach(System.out::println);
    }

    @Test
    public void testScannerClassNameListByRpcService() throws Exception {
//        RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService("io.learn.rpc.test.scanner");
    }

    @Test
    public void testScannerClassNameListByRpcReference() throws Exception {
        RpcReferenceScanner.doScannerWithRpcReferenceAnnotationFilter("io.learn.rpc.test.scanner");
    }

}

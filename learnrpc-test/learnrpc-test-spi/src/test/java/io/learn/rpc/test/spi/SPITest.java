package io.learn.rpc.test.spi;

import io.learn.rpc.spi.loader.ExtensionLoader;
import io.learn.rpc.test.spi.service.SPIService;
import org.junit.jupiter.api.Test;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.spi.service
 * @className: SPITest
 * @author: ycd20
 * @description: test spi
 * @date: 2022/11/20 11:40
 * @version: 1.0
 */
public class SPITest {
    @Test
    public void testSpiLoader() {
        SPIService spiService =
                ExtensionLoader.getExtension(SPIService.class, "spiService");

        String result = spiService.hello("learn");
        System.out.println(result);
    }
}

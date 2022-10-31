package registry;

import io.learn.rpc.protocol.meta.ServiceMeta;
import io.learn.rpc.registry.api.RegistryService;
import io.learn.rpc.registry.api.config.RegistryConfig;
import io.learn.rpc.registry.zookeeper.ZookeeperRegistryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.test.registry
 * @className: ZookeeperRegistryTest
 * @author: ycd20
 * @description: TODO
 * @date: 2022/11/2 20:36
 * @version: 1.0
 */
public class ZookeeperRegistryTest {
    private RegistryService registryService;
    private ServiceMeta serviceMeta;

    @BeforeEach
    public void init() throws Exception {
        RegistryConfig registryConfig = new RegistryConfig("127.0.0.1:2181", "zookeeper");
        this.registryService = new ZookeeperRegistryService();
        this.registryService.init(registryConfig);
        this.serviceMeta = new ServiceMeta(ZookeeperRegistryTest.class.getName(), "1.0.0", "learn",
                "127.0.0.1", 8080);
    }

    @Test
    public void testRegistry() throws Exception {
        this.registryService.register(serviceMeta);
    }

    @Test
    public void testUnRegistry() throws Exception {
        this.registryService.unRegister(serviceMeta);
    }

    @Test
    public void testDiscovery() throws Exception {
        this.registryService.discovery(RegistryService.class.getName(),
                "learn".hashCode());
    }

    @Test
    public void testDestroy() throws IOException {
        this.registryService.destroy();
    }
}

package gateway.bind;


import gateway.session.Configuration;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛化调用注册
 */
public class GenericReferenceRegistry {
    private final Configuration configuration;

    private final Map<String, GenericReferenceProxyFactory> registeredGenericReferences = new HashMap<>();

    public GenericReferenceRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public IGenericReference getGenericReference(String methodName) {
        GenericReferenceProxyFactory genericReferenceProxyFactory = registeredGenericReferences.get(methodName);
        if (genericReferenceProxyFactory == null) {
            throw new RuntimeException("Method " + methodName + " is not known to the GenericReferenceRegistry.");
        }
        return genericReferenceProxyFactory.newInstance(methodName);
    }

    public void addGenericReference(String application, String interfaceName, String methodName) {
        // 获取基础服务（创建成本较高，内存存放获取）
        ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
        RegistryConfig registryConfig = configuration.getRegistryConfig(application);
        ReferenceConfig<GenericService> reference = configuration.getReferenceConfig(interfaceName);
        // 构建Dubbo服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(reference).start();
        // 获取泛化调用服务
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);

        registeredGenericReferences.put(methodName, new GenericReferenceProxyFactory(genericService));
    }


}

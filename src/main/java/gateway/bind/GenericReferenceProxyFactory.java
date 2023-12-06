package gateway.bind;

import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.cglib.proxy.Enhancer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Handler;

/**
 * 泛化调用代理工厂：创建产生对某个服务下特定泛化调用的新代理
 */
public class GenericReferenceProxyFactory {

    private final GenericService genericService;

    // 一条http的请求对应到一个抽象包装代理后的泛化调用接口
    private Map<String, IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

    public GenericReferenceProxyFactory(GenericService genericService) {
        this.genericService = genericService;
    }


    public IGenericReference newInstance(String methodName) {
        return genericReferenceCache.computeIfAbsent(methodName, k -> {
            // 创建代理对象
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(IGenericReference.class);
            enhancer.setCallback(new GenericReferenceProxy(genericService, methodName));
            // 这一步的用处存疑？如果只是去测试rpc 真的有必要？
//            enhancer.setInterfaces();
            return (IGenericReference) enhancer.create();

        });
    }
}

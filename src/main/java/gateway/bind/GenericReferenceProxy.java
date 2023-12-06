package gateway.bind;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 泛化调用代理：可以视为对一个服务下其中一个方法调用的代理拦截
 */
public class GenericReferenceProxy implements MethodInterceptor {
    private final GenericService genericService;

    private final String methodName;


    public GenericReferenceProxy(GenericService genericService, String methodName) {
        this.genericService = genericService;
        this.methodName = methodName;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parametersTypeName = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parametersTypeName[i] = parameterTypes[i].getName();
        }
        return genericService.$invoke(methodName, parametersTypeName, args);
    }
}

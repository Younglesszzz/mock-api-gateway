package gateway.bind;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class GenericReferenceProxy implements MethodInterceptor {
    private final GenericService genericService;

    private final String genericCallMethodName;


    public GenericReferenceProxy(GenericService genericService, String genericCallMethodName) {
        this.genericService = genericService;
        this.genericCallMethodName = genericCallMethodName;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//        return genericService.$invoke(genericCallMethodName, paramters, args);
        return null;
    }
}

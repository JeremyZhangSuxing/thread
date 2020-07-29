package com.spring.cloud.cglib;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/7/23 22:48
 **/
@Data
@AllArgsConstructor
public class MyMethodInterceptor implements MethodInterceptor {
    private String methodName;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object object = new Object();
        if (method.getName().equals(methodName)) {
            //可以根据自己条件实现切面
            System.out.println(method.getName() + "---前置增强");
            object = methodProxy.invokeSuper(o, objects);
            System.out.println(method.getName() + "---后置增强");
        }
        return object;
    }
}

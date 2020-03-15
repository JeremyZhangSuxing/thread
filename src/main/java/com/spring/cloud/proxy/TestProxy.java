package com.spring.cloud.proxy;

import java.lang.reflect.Proxy;

/**
 * @author zhang.suxing
 * @date 2020/3/3 11:24
 **/
public class TestProxy {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        /**
         * 1.类加载器  (class的字节码对象获取)
         * 2.目标对象的所有接口  (class的字节码对象获取)
         * 3.需要以恶搞实现 InvocationHandle接口的对象 对目标对象方法的增强在这哥对象中完成
         *
         */
        Object proxyInstance = Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), new MyInvocationHandle(userDao));
        UserDao userDaoProxy = (UserDao) proxyInstance;
        userDaoProxy.add();
        userDaoProxy.delete();
    }
}

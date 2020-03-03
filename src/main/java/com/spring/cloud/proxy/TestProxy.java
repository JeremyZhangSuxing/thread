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
         * 类加载器  目标对象的所有接口 通过类字节码获取
         */
        Object proxyInstance = Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), new MyInvocationHandle(userDao));
        UserDao userDaoProxy = (UserDao) proxyInstance;
        userDaoProxy.add();
        userDaoProxy.delete();
    }
}

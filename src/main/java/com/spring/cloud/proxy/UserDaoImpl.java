package com.spring.cloud.proxy;

/**
 * @author zhang.suxing
 * @date 2020/3/3 11:23
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("--目標方法--add-");
    }

    @Override
    public void delete() {
        System.out.println("--目標方法--delete-");
    }
}

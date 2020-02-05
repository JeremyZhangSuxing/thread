package com.spring.cloud.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Hashtable;

/**
 * @author zhang.suxing
 * @date 2020/2/3 22:20
 **/
@AllArgsConstructor
@Data
public class User {
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 定位
     */
    private String position;


    public static void main(String[] args) {

//        User user = null;
//        Optional<User> optionalUser = Optional.ofNullable(user);
//        optionalUser.isPresent();
//        StringUtils.equals("",optionalUser.get().getEmail());
//        User user2 = new User("anna@gmail.com", "1234");
//        String email = Optional.ofNullable(user).map(User::getEmail).orElse("没有值呢");
//        System.out.println(email);


        User user1 = new User("", "");
        User user4 = new User("", "");
        System.out.println(user1.hashCode());
        System.out.println(user4.hashCode());
        System.out.println(user1.equals(user4));
        System.out.println(user1 == user4);
        System.out.println(System.identityHashCode(user1) + "<----->" + System.identityHashCode(user4));
        String a = "1";
        String b = new String("1");
        System.out.println(System.identityHashCode(a) + "<----->" + System.identityHashCode(b));

        Hashtable<String, String> hashTable = new Hashtable<>();
        hashTable.put("1", "1");
        hashTable.put("1", "2");
        hashTable.put(null, "q");
        for (String s : hashTable.keySet()) {
            System.out.println(hashTable.get(s));
        }
    }
}

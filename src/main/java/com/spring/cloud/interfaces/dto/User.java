package com.spring.cloud.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
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

        User user = new User("", "");
        User user1 = new User("1", "");
        User user4 = new User("1", "");
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
//        hashTable.put(null, "q");
        for (String s : hashTable.keySet()) {
            System.out.println(hashTable.get(s));
        }
        HashSet<User> userHashSet = new HashSet<>();
        userHashSet.add(user1);
        userHashSet.add(user4);
        for (User user5 : userHashSet) {
            System.out.println("——————" + user.hashCode());
        }
    }
}

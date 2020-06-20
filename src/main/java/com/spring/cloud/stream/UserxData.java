package com.spring.cloud.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang.suxing
 * @date 2020/6/14 21:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserxData {

    private String email;
    private String name;
    private String pwd;
    private Address address;

    public UserxData(String email, String pwd) {
        this.pwd = pwd;
        this.email = email;
    }
}

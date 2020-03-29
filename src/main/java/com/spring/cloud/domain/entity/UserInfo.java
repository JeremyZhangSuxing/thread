package com.spring.cloud.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data

public class UserInfo implements Serializable {
    private Long id;

    private String nickName;

    private String passWord;

    private Byte sex;

    private String phone;

    private String image;

    private Date createTime;

    private Date updateTime;

    private Byte deleted;


}
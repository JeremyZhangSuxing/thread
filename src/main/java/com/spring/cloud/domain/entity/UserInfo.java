package com.spring.cloud.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
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
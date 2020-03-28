package com.spring.cloud.domain.controller;

import com.spring.cloud.domain.entity.UserInfo;
import com.spring.cloud.domain.entity.UserInfoExample;
import com.spring.cloud.interfaces.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhang.suxing
 * @date 2019/11/22 10:31
 **/
@Controller
@RequestMapping("/template")
public class PageController {

    @RequestMapping("index")
    public String test() {
        return "index";
    }

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/test")
    public ResponseEntity<UserInfo> getById() {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andIdEqualTo(88L);
        return ResponseEntity.status(200).body(userInfoMapper.selectByExample(example).get(0));

    }
}

package com.spring.cloud.domain.controller;

import com.spring.cloud.application.AuthService;
import com.spring.cloud.domain.controller.dto.AuthResponseVo;
import com.spring.cloud.interfaces.dto.ModifyUserDto;
import com.spring.cloud.interfaces.dto.UserInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang.suxing
 * @date 2020/2/1 11:32
 **/
@RequestMapping("/auth")
@RestController
@Api(tags = {"登陆模块"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "用户注册接口")
    @PostMapping("/register")
    public ResponseEntity<AuthResponseVo> register(@RequestBody UserInfoDto userInfoDto) {
        return ResponseEntity.ok(authService.registerUserInfo(userInfoDto));
    }

    @ApiOperation(value = "用户登录接接口")
    @PostMapping("/sign")
    public ResponseEntity<AuthResponseVo> signIn(@RequestBody ModifyUserDto userDto) {
        return ResponseEntity.ok(authService.signIn(userDto.getNickName(), userDto.getPassWord()));
    }

    @ApiOperation(value = "用户修改密码接口")
    @PostMapping("/modifyUserInfo")
    public ResponseEntity<AuthResponseVo> modifyPassWord(@RequestBody ModifyUserDto userDto) {
        return ResponseEntity.ok(authService.modifyUserInfo(userDto));
    }
}

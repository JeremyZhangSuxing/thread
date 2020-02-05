package com.spring.cloud.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhang.suxing
 * @date 2020/2/1 14:17
 **/
@Data
@ApiModel(description = "请求参数")
public class UserInfoDto {

    @NotBlank
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String passWord;

    @NotNull
    @ApiModelProperty(value = "性别")
    private Byte sex;

    @NotBlank
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "用户头像（如果不填使用默认头像）")
    private String image;
}

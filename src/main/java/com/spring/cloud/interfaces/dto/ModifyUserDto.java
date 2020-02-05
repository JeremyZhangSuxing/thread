package com.spring.cloud.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhang.suxing
 * @date 2020/2/1 15:53
 **/
@Data
@ApiModel(value = "请求参数")
public class ModifyUserDto {

    @NotBlank
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String passWord;

    public static void main(String[] args) {
        String a = "sxuing";
        a = a + "1";
        System.out.println(a);

    }


}

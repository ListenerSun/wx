package com.sqt.edu.course.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-16 16:42
 */
@Data
@ApiModel("用户注册")
public class RegisterUserDTO implements Serializable {
    private static final long serialVersionUID = -2879013568917152620L;

    @ApiModelProperty("密码")
    @NotBlank
    private String password;

    @ApiModelProperty("手机号")
    @NotBlank
    private String phone;

    @ApiModelProperty("验证码")
    private String code;
}

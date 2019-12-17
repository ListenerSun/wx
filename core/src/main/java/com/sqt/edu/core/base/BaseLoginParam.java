package com.sqt.edu.core.base;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-04 10:55
 */
@Data
public class BaseLoginParam implements Serializable {
    private static final long serialVersionUID = -6925078109244185440L;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空!")
    private String phone;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空!")
    private String password;

}

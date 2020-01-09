package com.sqt.edu.course.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-27 14:15
 */
@ApiModel(value = "修改密码请求DTO")
@Builder
@Data
public class UpdatePasswordDTO implements Serializable {
    private static final long serialVersionUID = 3733070527004247209L;

    @NotBlank(message = "newPassword must not be null！ ")
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    @NotBlank(message = "oldPassword must not be null！ ")
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
}

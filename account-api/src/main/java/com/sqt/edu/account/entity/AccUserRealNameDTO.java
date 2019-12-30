package com.sqt.edu.account.entity;

import com.sqt.edu.core.validation.IdCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 用户认证入参
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-30 20:47
 */
@Data
@ApiModel("用户认证入参")
public class AccUserRealNameDTO implements Serializable {
    private static final long serialVersionUID = 626927322299612653L;

    @ApiModelProperty("身份证号")
    @IdCard
    private String idCard;
    @ApiModelProperty("身份正面照片")
    @NotBlank(message = "身份正面照片不能为空!")
    private String idCardPath1;
    @ApiModelProperty("身份反面照片")
    @NotBlank(message = "身份反面照片不能为空!")
    private String idCardpath2;
}

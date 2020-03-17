package com.sqt.edu.course.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-17 16:02
 */
@ApiModel(value = "用户基本信息")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccUserInfoDTO implements Serializable {

    private static final long serialVersionUID = 447365611438592525L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "性别 M:男 F:女 O:其他")
    private String sex;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户类型 0:管理员 1:普通老师 2:专业讲师 3:普通用户 ")
    private String type;

    @ApiModelProperty(value = "微信id")
    private String wechatId;

    @ApiModelProperty(value = "在线状态　０:不在线  １:在线")
    private String online;

    @ApiModelProperty(value = "用户状态　０:禁用　　１:可用")
    private String state;

    @ApiModelProperty(value = "认证状态(1未认证2认证中3已认证4认证失败)")
    private String authState;

    @ApiModelProperty(value = "审核时间")
    private Date authTime;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "用户实际姓名")
    private String realName;

    @ApiModelProperty(value = "身份证正面照")
    private String idCardPath1;

    @ApiModelProperty(value = "身份证反面照")
    private String idCardPath2;
}

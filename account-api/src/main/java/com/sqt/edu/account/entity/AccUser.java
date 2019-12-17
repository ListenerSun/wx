package com.sqt.edu.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sqt.edu.core.annotation.EduTable;
import com.sqt.edu.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 用户表
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-04 10:15
 */
@ApiModel
@Builder
@Data
@TableName(value = "acc_user")
public class AccUser extends BaseModel {

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "性别 M:男 F:女 O:其他")
    private String sex;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户类型 0:管理员 1:teacher 2:student 3:parent")
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
    private Date auditTime;

    @ApiModelProperty(value = "身份证正面照")
    private String idCardPath1;

    @ApiModelProperty(value = "身份证反面照")
    private String idCardPath2;

}

package com.sqt.edu.core.entity;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-26 15:10
 */

import com.sqt.edu.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评论
 */
@Data
@ApiModel(description = "评论")
public class Comments extends BaseModel {

    private Long id;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论人真实姓名")
    private String realName;

    @ApiModelProperty("评论人昵称")
    private String nickName;

    @ApiModelProperty("联系方式")
    private String connect;

    @ApiModelProperty("个人网站")
    private String site;

    @ApiModelProperty("回复对象的昵称")
    private String replyNickName;

    @ApiModelProperty("是否是管理员回复")
    private boolean replyAdmin;

    @ApiModelProperty(value = "评论的类型",
            notes = "article,talk,message等")
    private String type;

    @ApiModelProperty(value = "相关id",
            notes = "分别对应article.id, talk.id，若为0表示为message")
    private Long relationId;

    @ApiModelProperty("父评论id，若为0则表示没有父评论")
    private Long pid;

}

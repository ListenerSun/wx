package com.sqt.edu.account.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MsgEnum
 *
 * @author cyj
 * @date 19-4-25
 */
@AllArgsConstructor
@Getter
public enum MsgEnum {

    /**
     * 验证码注册
     */
    USER_REG(1L,
            "验证码注册",
            "验证码 ${code}，您正在注册好想法用户，谨防用户隐私信息泄露。")


    ;

    /**
     * msg 类型，唯一
     */
    Long msgId;
    /**
     * msg 类型
     */
    String type;
    /**
     * msg 内容
     */
    String content;

}

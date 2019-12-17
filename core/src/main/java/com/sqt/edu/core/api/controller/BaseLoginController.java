package com.sqt.edu.core.api.controller;

import com.sqt.edu.core.base.BaseExceptionMsg;
import com.sqt.edu.core.base.BaseLoginParam;
import com.sqt.edu.core.base.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-04 10:51
 */
public abstract class BaseLoginController<T extends BaseLoginParam> {

    /**根据用户名 密码 登入
     * @param baseLoginParam
     * @return
     */
    @PostMapping("/login-by-password")
    public JsonResult loginByPassword(@RequestBody BaseLoginParam baseLoginParam){
        return new JsonResult(BaseExceptionMsg.INTER_NOT_SURPPORT);
    }

    /**
     * 验证码登录
     *
     * @param loginParamVo 登录vo
     * @return
     */
    @PostMapping("/login-by-verificationcode")
    public JsonResult loginByVerificationcode(@RequestBody T loginParamVo) {
        return new JsonResult(BaseExceptionMsg.INTER_NOT_SURPPORT);
    }

    /**
     * 微信登录
     *
     * @param loginParamVo 登录vo
     * @return
     */
    @PostMapping("/login-by-wechat")
    public JsonResult loginByWechat(@RequestBody T loginParamVo) {
        return new JsonResult(BaseExceptionMsg.INTER_NOT_SURPPORT);
    }

}

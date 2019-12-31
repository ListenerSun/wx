package com.sqt.edu.account.client;

import com.sqt.edu.core.base.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-31 15:05
 */
@FeignClient(value = "edu-account", path = "/realname")
public interface AccUserRealNameServiceClient {

    /**校验用户是否 实名认证
     * @param userId
     * @return
     */
    @GetMapping("/acc_real_name")
    JsonResult authRealName(@NotNull @RequestParam Long userId);

}

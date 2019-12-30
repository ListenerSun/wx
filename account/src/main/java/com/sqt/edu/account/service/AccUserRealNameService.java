package com.sqt.edu.account.service;

import com.sqt.edu.account.entity.AccUserRealNameDTO;
import com.sqt.edu.core.base.JsonResult;

/**
 * @Description: 实名认证
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-30 20:04
 */
public interface AccUserRealNameService {

    /**实名认证
     * @param accUserRealNameDTO
     * @return
     */
    JsonResult realName(AccUserRealNameDTO accUserRealNameDTO);
}

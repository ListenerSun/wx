package com.sqt.edu.account.controller;

import com.sqt.edu.account.entity.AccUserRealNameDTO;
import com.sqt.edu.account.service.AccUserRealNameService;
import com.sqt.edu.core.base.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-30 20:01
 */
@Slf4j
@Api(tags = "【用户实名认证相关接口】")
@RestController
@RequestMapping("/realname")
public class AccUserRealNameController {

    @Autowired
    private AccUserRealNameService accUserRealNameService;

    @ApiOperation(value = "A-5-实名认证")
    @PostMapping("/acc_user")
    public JsonResult realName(@RequestBody @Valid AccUserRealNameDTO accUserRealNameDTO){
        return accUserRealNameService.realName(accUserRealNameDTO);
    }
}

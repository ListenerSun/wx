package com.sqt.edu.account.controller;

import com.sqt.edu.account.request.RegisterUserDTO;
import com.sqt.edu.account.service.AccUserService;
import com.sqt.edu.core.base.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-16 14:41
 */
@Slf4j
@Api(tags = "【用户相关接口】")
@RestController
@RequestMapping("/user")
public class AccUserController {

    @Autowired
    private AccUserService accUserService;

    @ApiOperation(value = "A-1-创建用户")
    @PostMapping("/create")
    public JsonResult create(@RequestBody @Valid RegisterUserDTO registerUserDTO){
        return new JsonResult(accUserService.create(registerUserDTO));
    }
    @ApiOperation(value = "A-2-注册发送送验证码")
    @GetMapping("/register_code/{phone}")
    public JsonResult sendRegisterCode(@PathVariable String phone){
        accUserService.sendRegisterCode(phone);
        return new JsonResult();
    }


}

package com.sqt.edu.course.controller;

import com.sqt.edu.course.request.RegisterUserDTO;
import com.sqt.edu.course.request.UpdatePasswordDTO;
import com.sqt.edu.course.service.AccUserService;
import com.sqt.edu.core.base.BaseLoginParam;
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

    @ApiOperation(value = "A-3-账号密码登入")
    @PostMapping("/login_by_password")
    public JsonResult loginByPassword(@RequestBody @Valid BaseLoginParam loginParam){
        return accUserService.loginByPassword(loginParam);
    }
    @ApiOperation(value = "A-4-修改密码")
    @PostMapping("/update_password")
    public JsonResult updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO){
        return accUserService.updatePassword(updatePasswordDTO);
    }

}

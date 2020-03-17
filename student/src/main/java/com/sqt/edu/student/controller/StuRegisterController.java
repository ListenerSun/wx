package com.sqt.edu.student.controller;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.student.dto.request.QueryStuRegisterInfoDTO;
import com.sqt.edu.student.dto.request.StuRegisterInfoDTO;
import com.sqt.edu.student.service.StuRegisterInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-13 23:23
 */
@Api(tags = "【学生报名信息接口】")
@RestController
@RequestMapping("/class_order")
public class StuRegisterController {

    @Autowired
    private StuRegisterInfoService stuRegisterInfoService;

    @ApiOperation(value = "S-1.1-学生预约报名接口")
    @PostMapping("/add")
    public JsonResult add(@RequestBody @Valid StuRegisterInfoDTO stuRegisterInfoDTO){
        return stuRegisterInfoService.add(stuRegisterInfoDTO);
    }
    @ApiOperation(value = "S-1.2-查询是否已经报名接口")
    @PostMapping("/query")
    public JsonResult queryStuRegisterInfo(@RequestBody @Valid QueryStuRegisterInfoDTO queryStuRegisterInfoDTO){
        return stuRegisterInfoService.queryStuRegisterInfo(queryStuRegisterInfoDTO);
    }
}

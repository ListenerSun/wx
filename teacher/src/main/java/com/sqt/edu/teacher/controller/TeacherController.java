package com.sqt.edu.teacher.controller;

import com.netflix.discovery.converters.Auto;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.teacher.request.TeacherInfoDTO;
import com.sqt.edu.teacher.service.TeacherService;
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
 * @Date: Created in 2019-12-24 18:18
 */
@Api(tags = "【老师个人信息接口】")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/auth_teacher")
    public JsonResult add(@RequestBody @Valid TeacherInfoDTO teacherInfoDTO){
        return teacherService.add(teacherInfoDTO);
    }

}

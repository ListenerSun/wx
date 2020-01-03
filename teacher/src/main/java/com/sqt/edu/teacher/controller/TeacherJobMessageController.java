package com.sqt.edu.teacher.controller;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.teacher.request.TeacherJobMessageDTO;
import com.sqt.edu.teacher.service.TeacherJobMessageService;
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
 * @Date: Created in 2020-01-03 16:52
 */
@RestController
@Api(tags = "【老师发布家教信息相关接口】")
@RequestMapping("/edu/teacher_job")
public class TeacherJobMessageController {

    @Autowired
    private TeacherJobMessageService teacherJobMessageService;

    @ApiOperation(value = "J-1-发布一条教教信息")
    @PostMapping("create")
    public JsonResult create(@RequestBody @Valid TeacherJobMessageDTO teacherJobMessageDTO){
        return teacherJobMessageService.create(teacherJobMessageDTO);
    }
}

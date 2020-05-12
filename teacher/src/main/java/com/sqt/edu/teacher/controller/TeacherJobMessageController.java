package com.sqt.edu.teacher.controller;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.teacher.request.TeacherJobMessageDTO;
import com.sqt.edu.teacher.service.TeacherJobMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
    @ApiOperation(value = "J-2-更新一条教教信息")
    @PostMapping("update")
    public JsonResult update(@RequestBody @Valid TeacherJobMessageDTO teacherJobMessageDTO){
        return teacherJobMessageService.update(teacherJobMessageDTO);
    }
    @ApiOperation(value = "J-3-删除一条教教信息")
    @PostMapping("delete")
    public JsonResult delete(@RequestBody @Valid @NotBlank Long teacherJobMessageId){
        return teacherJobMessageService.delete(teacherJobMessageId);
    }
    @ApiOperation(value = "J-4-查询某个教师发布的信息列表")
    @PostMapping("list_by_teacherId")
    public JsonResult listByTeacherId(@RequestBody @Valid @NotBlank Long userId){
        return teacherJobMessageService.selectByTeacherId(userId);
    }

    /**********************************************用户端需要的接口******************************/

    @ApiOperation(value = "TeacherMessageJob-C-1-1查询所有贴心老师列表")
    @GetMapping("list_teacher_job_message")
    public JsonResult listTeacherJobMessage(@RequestParam int pageSize, @RequestParam int pageNum){
        return teacherJobMessageService.listTeacherJobMessage(pageSize,pageNum);
    }
}

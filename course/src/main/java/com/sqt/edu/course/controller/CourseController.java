package com.sqt.edu.course.controller;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.course.request.CourseDTO;
import com.sqt.edu.course.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-13 14:10
 */
@Api(tags = "【课程相关APi】")
@RestController
@RequestMapping("/edu/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    @ApiOperation(value = "C-1.1-创建一个课程")
    public JsonResult create(@RequestBody @Valid CourseDTO courseDTO) {
        return courseService.create(courseDTO);
    }

    @GetMapping("/{courseId}")
    @ApiOperation(value = "C-1.2-根绝courseId查询课程详细信息")
    public JsonResult getCourseInfoById(@PathVariable @Valid @NotNull Long courseId){
        return courseService.getCourseInfoById(courseId);
    }

    @GetMapping("list_by_teacher_id/{teacherId}")
    public JsonResult listByTeacherId(@PathVariable @Valid @NotNull Long teacherId){
        return courseService.listByTeacherId(teacherId);
    }
}

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
import java.util.List;

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

    /**********************************************后台管理端需要的接口******************************/

    @PostMapping("/create")
    @ApiOperation(value = "C-1.1-创建一个课程")
    public JsonResult create(@RequestBody @Valid CourseDTO courseDTO) {
        return courseService.create(courseDTO);
    }

    @GetMapping("/{courseId}")
    @ApiOperation(value = "C-1.2-根据courseId查询课程详细信息")
    public JsonResult getCourseInfoById(@PathVariable @Valid @NotNull Long courseId) {
        return courseService.getCourseInfoById(courseId);
    }

    @GetMapping("/list_by_teacher_id/{teacherId}")
    @ApiOperation(value = "C-1.3-根据teacherId查询课程列表")
    public JsonResult listByTeacherId(@PathVariable @Valid @NotNull Long teacherId) {
        return courseService.listByTeacherId(teacherId);
    }

    @GetMapping("/list")
    @ApiOperation(value = "C-1.4-查询课程列表")
    public JsonResult list(@RequestParam int pageSize, @RequestParam int pageNum) {
        return courseService.list(pageSize, pageNum);
    }

    @GetMapping("/course_info/{id}")
    @ApiOperation(value = "C-1.5-查询课程详情信息")
    public JsonResult courseInfo(@PathVariable Long id, @RequestParam int pageSize, @RequestParam int pageNum) {
        return courseService.courseInfo(id, pageSize, pageNum);
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "C-1.6-删除课程")
    public JsonResult delete(@PathVariable Long id) {
        return courseService.delete(id);
    }

    @PostMapping("/upShelf")
    @ApiOperation(value = "C-1.7-上架课程")
    public JsonResult upShelf(@RequestBody List<Long> ids) {
        return courseService.upShelf(ids);
    }

    @PostMapping("/downShelf")
    @ApiOperation(value = "C-1.8-下架课程")
    public JsonResult downShelf(@RequestBody List<Long> ids) {
        return courseService.downShelf(ids);
    }

    /**********************************************用户端需要的接口******************************/
    @GetMapping("/discover")
    @ApiOperation(value = "C-10-查询推荐课程")
    public JsonResult listDiscoverCourses() {
        return courseService.listDiscoverCourses();
    }

}

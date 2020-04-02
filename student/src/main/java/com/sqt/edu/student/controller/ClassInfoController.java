package com.sqt.edu.student.controller;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.student.dto.request.ClassInfoDTO;
import com.sqt.edu.student.dto.request.QueryClassInfoDTO;
import com.sqt.edu.student.service.ClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-14 17:14
 */
@Api(tags = "【补课班级信息接口】")
@RequestMapping("/class_info")
@RestController
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;

    /**********************************************后台管理端需要的接口******************************/

    @PostMapping("/add")
    @ApiOperation(value = "C2-1.1-新增补课班级信息")
    public JsonResult add(@RequestBody @Valid ClassInfoDTO classInfoDTO) {
        return classInfoService.add(classInfoDTO);
    }

    @PostMapping("/update")
    @ApiOperation(value = "C2-1.2-更新补课班级信息")
    public JsonResult update(@RequestBody @Valid ClassInfoDTO classInfoDTO) {
        return classInfoService.update(classInfoDTO);
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "C2-1.3-删除补课班级信息")
    public JsonResult delete(@PathVariable(value = "id") Long classInfoId) {
        return classInfoService.delete(classInfoId);
    }

    @PostMapping("/list")
    @ApiOperation(value = "C2-1.4-查询补课班级信息列表")
    public JsonResult list(@RequestBody(required = false) QueryClassInfoDTO queryClassInfoDTO) {
        return classInfoService.list(queryClassInfoDTO);
    }

    @GetMapping("/enroll/{id}")
    @ApiOperation(value = "C2-1.5-发布招生接口")
    public JsonResult enrollClass(@PathVariable(value = "id") Long classInfoId) {
        return classInfoService.enrollClass(classInfoId);
    }

    /**********************************************用户端需要的接口******************************/

    @PostMapping("/enroll/list")
    @ApiOperation(value = "C2-1.5-获取正在招生的补课班级列表")
    public JsonResult enrollClassInfoList() {
        return classInfoService.enrollClassInfoList();
    }
}

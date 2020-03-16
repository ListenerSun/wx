package com.sqt.edu.student.controller;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.student.dto.request.ClassInfoDTO;
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
    @DeleteMapping("/delete/{classInfoId}")
    @ApiOperation(value = "C2-1.3-删除补课班级信息")
    public JsonResult delete(@PathVariable @Valid Long classInfoId) {
        return classInfoService.delete(classInfoId);
    }
}

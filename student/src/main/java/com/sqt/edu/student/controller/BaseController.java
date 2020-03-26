//package com.sqt.edu.student.controller;
//
//import com.sqt.edu.core.base.JsonResult;
//import com.sqt.edu.student.service.BaseService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @Description:
// * @author: ListenerSun(男, 未婚) 微信:810548252
// * @Date: Created in 2020-03-22 18:19
// */
//@Api
////@RestController
//public class BaseController<S extends BaseService> {
////    @Autowired
//    private S service;
//
//    @ApiOperation("导出excel")
//    @PostMapping("/exportExcel")
//    public JsonResult exportExcel(@RequestBody List<Long> ids) {
//        return service.exportExcel(ids);
//    }
//}

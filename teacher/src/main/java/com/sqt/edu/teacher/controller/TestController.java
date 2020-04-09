package com.sqt.edu.teacher.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.exception.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-11-27 17:12
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @SentinelResource("/test/hello")
    @GetMapping("/hello")
    public JsonResult test() {
//        if (1 == 1) {
//            throw new ServiceException("teacher服务出异常了!");
//        }
        return new JsonResult("test");
    }
}

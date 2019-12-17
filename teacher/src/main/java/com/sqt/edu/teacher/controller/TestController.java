package com.sqt.edu.teacher.controller;

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

    @GetMapping("/hello")
    public String test(){
        return "test";
    }
}

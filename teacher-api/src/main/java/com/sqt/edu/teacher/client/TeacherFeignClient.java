package com.sqt.edu.teacher.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-24 18:34
 */
@FeignClient(value = "edu-teacher",path = "/edu")
public interface TeacherFeignClient {
}

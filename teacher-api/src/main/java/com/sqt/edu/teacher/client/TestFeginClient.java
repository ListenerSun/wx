package com.sqt.edu.teacher.client;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.teacher.client.fallback.TeacherFeignClientFallBack;
import com.sqt.edu.teacher.client.fallback.TestFeginClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-02 10:10
 */
@FeignClient(name = "edu-teacher", path = "/teacher/test", fallback = TestFeginClientFallBack.class)
public interface TestFeginClient {

    @GetMapping("/hello")
    JsonResult test();
}

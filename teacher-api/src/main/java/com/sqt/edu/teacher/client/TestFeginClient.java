package com.sqt.edu.teacher.client;

import com.sqt.edu.teacher.client.fallback.TeacherFeignClientFallBack;
import com.sqt.edu.teacher.client.fallback.TestFeginClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-02 10:10
 */
@FeignClient(value = "edu-teacher", path = "/edu/teacher", fallbackFactory = TestFeginClientFallBack.class)
public interface TestFeginClient {

    @GetMapping("/hello")
    String test();
}

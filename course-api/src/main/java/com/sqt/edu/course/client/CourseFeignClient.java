package com.sqt.edu.course.client;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.course.client.fallback.CourseFeignClientFallBack;
import com.sqt.edu.course.request.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-13 14:55
 */
@FeignClient(value = "edu-teacher", path = "/edu/teacher", fallbackFactory = CourseFeignClientFallBack.class)
public interface CourseFeignClient {

    /**
     * create
     *
     * @param courseDTO
     * @return
     */
    @PostMapping("/create")
    JsonResult create(@RequestBody @Valid CourseDTO courseDTO);
}

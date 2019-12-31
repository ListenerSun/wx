package com.sqt.edu.teacher.client;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.teacher.client.fallback.TeacherFeignClientFallBack;
import com.sqt.edu.teacher.request.TeacherInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-24 18:34
 */
@FeignClient(value = "edu-teacher",path = "/edu/teacher",fallbackFactory = TeacherFeignClientFallBack.class)
public interface TeacherFeignClient {

    /**add a teacher info
     * @param teacherInfoDTO
     * @return
     */
    @PostMapping("/auth_teacher")
    JsonResult add(@RequestBody @Valid TeacherInfoDTO teacherInfoDTO);
}

package com.sqt.edu.teacher.client;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.teacher.client.fallback.TeacherFeignClientFallBack;
import com.sqt.edu.teacher.entity.TeacherInfo;
import com.sqt.edu.teacher.request.TeacherInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-24 18:34
 */
@FeignClient(name = "edu-teacher",path = "/teacher/edu/teacher",fallbackFactory = TeacherFeignClientFallBack.class)
public interface TeacherFeignClient {

    /**add a teacher info
     * @param teacherInfoDTO
     * @return
     */
    @PostMapping("/auth_teacher")
    JsonResult add(@RequestBody @Valid TeacherInfoDTO teacherInfoDTO);

    /**根据acc_user_id查询老师信息
     * @param accUserId
     * @return
     */
    @GetMapping("info_by_acc_user_id")
    JsonResult<TeacherInfo> getTeacherInfoByAccUserId(@RequestParam Long accUserId);
}

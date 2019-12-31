package com.sqt.edu.teacher.service;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.teacher.request.TeacherInfoDTO;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-24 18:22
 */
public interface TeacherService {

    /** add a teacher info
     * @param teacherInfoDTO
     * @return
     */
    JsonResult add(TeacherInfoDTO teacherInfoDTO);
}

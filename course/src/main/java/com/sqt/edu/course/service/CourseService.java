package com.sqt.edu.course.service;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.course.request.CourseDTO;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-13 14:41
 */
public interface CourseService {

    /**create a course
     * @param courseDTO
     * @return
     */
    JsonResult create(CourseDTO courseDTO);
}

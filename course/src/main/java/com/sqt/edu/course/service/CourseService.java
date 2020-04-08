package com.sqt.edu.course.service;

import com.sqt.edu.common.base.JsonResult;
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

    /**get a course detail message by courseId
     * @param courseId
     * @return
     */
    JsonResult getCourseInfoById(Long courseId);

    /**get course list by teacherId
     * @param teacherId
     * @return
     */
    JsonResult listByTeacherId(Long teacherId);

    /**查询 course list
     * @return
     */
    JsonResult list();
}

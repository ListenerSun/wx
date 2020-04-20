package com.sqt.edu.course.service;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.course.request.CourseDTO;

import java.util.List;

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
     * @param pageSize
     * @param pageNum
     * @return
     */
    JsonResult list(int pageSize,int pageNum);

    /**查询 课程详情信息
     * @param id
     * @param pageSize
     * @param pageNum
     * @return
     */
    JsonResult courseInfo(Long id,int pageSize,int pageNum);

    /**删除课程
     * @param id
     * @return
     */
    JsonResult delete(Long id);

    /**上架课程
     * @param ids
     * @return
     */
    JsonResult upShelf(List<Long> ids);

    /**下架课程
     * @param ids
     * @return
     */
    JsonResult downShelf(List<Long> ids);
}

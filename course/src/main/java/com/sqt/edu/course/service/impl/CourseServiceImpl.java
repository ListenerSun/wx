package com.sqt.edu.course.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.core.utils.RequestHelper;
import com.sqt.edu.course.constant.CourseConstant;
import com.sqt.edu.course.entity.Course;
import com.sqt.edu.course.mapper.CourseMapper;
import com.sqt.edu.course.request.CourseDTO;
import com.sqt.edu.course.response.CourseInfo;
import com.sqt.edu.course.service.CourseService;
import com.sqt.edu.teacher.client.TeacherFeignClient;
import com.sqt.edu.teacher.entity.TeacherInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-13 14:48
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private TeacherFeignClient teacherFeignClient;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public JsonResult create(CourseDTO courseDTO) {
        Long accUserId = RequestHelper.getUserId();
        JsonResult<TeacherInfo> teacherInfoJsonResult = teacherFeignClient.getTeacherInfoByAccUserId(accUserId);
        log.info("==========>调用edu-teacher服务,根据accUserId查询老师信息返回结果:{}", teacherInfoJsonResult);
        Course course = Course.builder()
                .courseName(courseDTO.getCourseName())
                .coursePrice(courseDTO.getCoursePrice())
                .isFree(courseDTO.getIsFree())
                .courseLogo(courseDTO.getCourseLogo())
                .countBuy(CourseConstant.COURSE_COUNT_BUY_DEFAULT)
                .countStudy(CourseConstant.COURSE_COUNT_STUDY_DEFAULT)
                .teacherId(teacherInfoJsonResult.getData().getId())
                .build();
        courseMapper.insert(course);
        return new JsonResult();
    }

    @Override
    public JsonResult getCourseInfoById(Long courseId) {
        Course course = courseMapper.selectById(courseId);
        if (null == course) {
            log.error("==========> 课程不存在! courseId:{}", courseId);
            throw new ServiceException(ResultCode.COURSE_NOT_EXIST);
        }
        CourseInfo courseInfo = CourseInfo.builder()
                .countBuy(course.getCountBuy())
                .countStudy(course.getCountStudy())
                .courseLogo(course.getCourseLogo())
                .coursePrice(course.getCoursePrice())
                .isFree(course.getIsFree())
                .courseName(course.getCourseName())
                .build();
        return new JsonResult(courseInfo);
    }

    @Override
    public JsonResult listByTeacherId(Long teacherId) {
        List<Course> courseList = courseMapper.selectList(Wrappers.<Course>lambdaQuery().eq(Course::getTeacherId, teacherId));
        List<CourseInfo> courseInfoList = courseList.stream()
                .map(course -> {
                    CourseInfo courseInfo = new CourseInfo();
                    BeanUtils.copyProperties(course, courseInfo);
                    return courseInfo;
                })
                .collect(Collectors.toList());
        return new JsonResult(courseInfoList);
    }

}

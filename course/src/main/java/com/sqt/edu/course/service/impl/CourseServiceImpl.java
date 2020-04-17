package com.sqt.edu.course.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.base.PageResult;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.exception.ServiceException;
import com.sqt.edu.common.utils.PageEduHelper;
import com.sqt.edu.common.utils.RequestHelper;
import com.sqt.edu.course.constant.CourseConstant;
import com.sqt.edu.course.entity.Course;
import com.sqt.edu.course.mapper.CourseInfoMapper;
import com.sqt.edu.course.mapper.CourseMapper;
import com.sqt.edu.course.request.CourseDTO;
import com.sqt.edu.course.response.CourseInfoVO;
import com.sqt.edu.course.response.CourseVO;
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
    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public JsonResult create(CourseDTO courseDTO) {
        Long accUserId = RequestHelper.getUserId();
        JsonResult<TeacherInfo> teacherInfoJsonResult = teacherFeignClient.getTeacherInfoByAccUserId(accUserId);
        log.info("==========>调用edu-teacher服务,根据accUserId查询老师信息返回结果:{}", JSON.toJSONString(teacherInfoJsonResult));
        Course course = Course.builder()
                .courseName(courseDTO.getCourseName())
                .coursePrice(courseDTO.getCoursePrice())
                .isFree(courseDTO.getIsFree())
                .courseLogo(courseDTO.getCourseLogo())
                .countBuy(CourseConstant.COURSE_COUNT_BUY_DEFAULT)
                .countStudy(CourseConstant.COURSE_COUNT_STUDY_DEFAULT)
                .teacherId(null == teacherInfoJsonResult.getData() ? -1L : teacherInfoJsonResult.getData().getId())
                .courseState(CourseConstant.COURSE_STATE_0)
                .build();
        courseMapper.insert(course);
        log.info("=========> 新增课程成功");
        return new JsonResult();
    }

    @Override
    public JsonResult getCourseInfoById(Long courseId) {
        Course course = courseMapper.selectById(courseId);
        if (null == course) {
            log.error("==========> 课程不存在! courseId:{}", courseId);
            throw new ServiceException(ResultCode.COURSE_NOT_EXIST);
        }
        CourseVO courseVO = CourseVO.builder()
                .countBuy(course.getCountBuy())
                .countStudy(course.getCountStudy())
                .courseLogo(course.getCourseLogo())
                .coursePrice(course.getCoursePrice())
                .isFree(course.getIsFree())
                .courseName(course.getCourseName())
                .build();
        return new JsonResult(courseVO);
    }

    @Override
    public JsonResult listByTeacherId(Long teacherId) {
        List<Course> courseList = courseMapper.selectList(Wrappers.<Course>lambdaQuery().eq(Course::getTeacherId, teacherId));
        List<CourseVO> courseVOList = courseList.stream()
                .map(course -> {
                    CourseVO courseVO = new CourseVO();
                    BeanUtils.copyProperties(course, courseVO);
                    return courseVO;
                })
                .collect(Collectors.toList());
        return new JsonResult(courseVOList);
    }

    @Override
    public JsonResult list(int pageSize,int pageNum) {
        PageResult<CourseVO> courseVOPageResult = PageEduHelper.selectPageResult(pageSize, pageNum, () -> courseMapper.listAll());
        return new JsonResult(courseVOPageResult);
    }

    @Override
    public JsonResult courseInfo(Long id,int pageSize,int pageNum) {
        PageResult<CourseInfoVO> courseInfoVOPageResult = PageEduHelper.selectPageResult(pageSize, pageNum,
                () -> courseInfoMapper.selectByCourseId(id));
        return new JsonResult(courseInfoVOPageResult);
    }

}

package com.sqt.edu.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.course.entity.Course;
import com.sqt.edu.course.response.CourseVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-13 14:49
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**查询所有的课程列表
     * @return
     */
    @Select(value = "select * from course")
    List<CourseVO> listAll();
}

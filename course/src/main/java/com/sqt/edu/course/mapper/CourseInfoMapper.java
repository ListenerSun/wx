package com.sqt.edu.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.course.entity.CourseInfo;
import com.sqt.edu.course.response.CourseInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-17 14:56
 */
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

    /**查询课程章节信息列表
     * @param id
     * @return
     */
    @Select(value = " select * from course_info where course_id = #{id,jdbcType=BIGINT}")
    List<CourseInfoVO> selectByCourseId(@Param("id") Long id);
}

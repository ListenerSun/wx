package com.sqt.edu.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.teacher.entity.TeacherJobMessage;
import com.sqt.edu.teacher.response.TeacherJobMessageVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-03 19:27
 */
public interface TeacherJobMessageMapper extends BaseMapper<TeacherJobMessage> {

    /**********************************************用户端需要的接口******************************/

    /**
     * 查询列表
     * @return
     */
    @Select(value = "select tjm.*,ti.graduate_school,ti.teacher_name,ti.teacher_logo from teacher_job_message tjm , " +
                 "teacher_info ti where tjm.teacher_id = ti.id")
    List<TeacherJobMessageVO> listTeacherJobMessage();
}

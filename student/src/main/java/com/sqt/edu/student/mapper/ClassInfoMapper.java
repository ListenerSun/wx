package com.sqt.edu.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.student.dto.request.ClassInfoDTO;
import com.sqt.edu.student.dto.request.QueryClassInfoDTO;
import com.sqt.edu.student.entity.ClassInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-12 21:33
 */
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    /**
     * 根据条件查询补课班级信息列表
     *
     * @param queryClassInfoDTO
     * @return
     */
    List<ClassInfoDTO> list(QueryClassInfoDTO queryClassInfoDTO);

    /**
     * 获取正在招生的补课班级列表
     *
     * @return
     */
    @Select(value = " select * from class_info where enroll_state = '1' or enroll_state = '2' ")
    List<ClassInfoDTO> enrollClassInfoList();
}


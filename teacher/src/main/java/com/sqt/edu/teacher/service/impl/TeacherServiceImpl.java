package com.sqt.edu.teacher.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.teacher.entity.TeacherInfo;
import com.sqt.edu.teacher.mapper.TeacherInfoMapper;
import com.sqt.edu.teacher.request.TeacherInfoDTO;
import com.sqt.edu.teacher.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-25 11:43
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Override
    public JsonResult add(TeacherInfoDTO teacherInfoDTO) {
        TeacherInfo teacherInfo = new TeacherInfo();
        BeanUtils.copyProperties(teacherInfoDTO,teacherInfo);
        teacherInfoMapper.insert(teacherInfo);
        return new JsonResult();
    }

    @Override
    public JsonResult getTeacherInfoByAccUserId(Long accUserId) {
        TeacherInfo teacherInfo = teacherInfoMapper.selectOne(Wrappers.<TeacherInfo>lambdaQuery().eq(TeacherInfo::getAccUserId, accUserId));
        if (null == teacherInfo){
            log.warn("==========>查询教师信息不存在。accUserId:{}",accUserId);
            return new JsonResult(ResultCode.TEA_NOT_EXIST);
        }
        return new JsonResult(teacherInfo);
    }
}

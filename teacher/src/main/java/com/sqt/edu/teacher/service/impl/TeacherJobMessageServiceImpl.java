package com.sqt.edu.teacher.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.base.JsonResultHandler;
import com.sqt.edu.common.base.PageResult;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.exception.ServiceException;
import com.sqt.edu.common.utils.PageEduHelper;
import com.sqt.edu.common.utils.RequestHelper;
import com.sqt.edu.teacher.entity.TeacherInfo;
import com.sqt.edu.teacher.entity.TeacherJobMessage;
import com.sqt.edu.teacher.mapper.TeacherInfoMapper;
import com.sqt.edu.teacher.mapper.TeacherJobMessageMapper;
import com.sqt.edu.teacher.request.TeacherJobMessageDTO;
import com.sqt.edu.teacher.response.TeacherJobMessageVO;
import com.sqt.edu.teacher.service.TeacherJobMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-03 19:27
 */
@Slf4j
@Service
public class TeacherJobMessageServiceImpl implements TeacherJobMessageService {

    @Autowired
    private TeacherJobMessageMapper teacherJobMessageMapper;
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Override
    public JsonResult create(TeacherJobMessageDTO teacherJobMessageDTO) {
        Long userId = RequestHelper.getUserId();
        TeacherInfo teacherInfo = teacherInfoMapper.selectOne(Wrappers.<TeacherInfo>lambdaQuery().eq(TeacherInfo::getAccUserId, userId));
        TeacherJobMessage teacherJobMessage = TeacherJobMessage.builder()
                .subject(teacherJobMessageDTO.getSubject())
                .grade(teacherJobMessageDTO.getGrade())
                .price(teacherJobMessageDTO.getPrice())
                .unit(teacherJobMessageDTO.getUnit())
                .teacherId(teacherInfo.getId())
                .graduateSchool(teacherInfo.getGraduateSchool())
                .accUserId(userId)
                .build();
        teacherJobMessageMapper.insert(teacherJobMessage);
        log.info("{} 成功发布一条家教信息！",teacherInfo.getTeacherName());
        return new JsonResult();
    }

    @Override
    public JsonResult update(TeacherJobMessageDTO teacherJobMessageDTO) {
        Assert.state(null != teacherJobMessageDTO.getTeacherJobMessageId(),"teacherJobMessageId 不能为空!");
        TeacherJobMessage oldTeacherJobMessage =
                teacherJobMessageMapper.selectById(teacherJobMessageDTO.getTeacherJobMessageId());
        BeanUtils.copyProperties(teacherJobMessageDTO,oldTeacherJobMessage);
        teacherJobMessageMapper.updateById(oldTeacherJobMessage);
        log.info("更新家教信息成功！ teacherJobMessageId：{}",teacherJobMessageDTO.getTeacherJobMessageId());
        return new JsonResult();
    }

    @Override
    public JsonResult delete(Long teacherJobMessageId) {
        teacherJobMessageMapper.deleteById(teacherJobMessageId);
        log.info("==========>删除家教信息成功! teacherJobMessageId：{}",teacherJobMessageId);
        return new JsonResult();
    }

    @Override
    public JsonResult selectByTeacherId(Long userId) {
        TeacherInfo teacherInfo = teacherInfoMapper.selectOne(Wrappers.<TeacherInfo>lambdaQuery().eq(TeacherInfo::getAccUserId, userId));
        if (null == teacherInfo){
            throw new ServiceException(ResultCode.MSG_NOT_READABLE);
        }
        List<TeacherJobMessage> teacherJobMessageList =
                teacherJobMessageMapper.selectList(Wrappers.<TeacherJobMessage>lambdaQuery().eq(TeacherJobMessage::getTeacherId, teacherInfo.getId()));
        return new JsonResult(teacherJobMessageList);
    }

    /**********************************************用户端需要的接口******************************/
    @Override
    public JsonResult listTeacherJobMessage(int pageSize, int pageNum) {
        PageResult<TeacherJobMessageVO> teacherJobMessagePageResult = PageEduHelper.selectPageResult(pageSize, pageNum, () -> teacherJobMessageMapper.listTeacherJobMessage());
        return JsonResultHandler.success(teacherJobMessagePageResult);
    }

}

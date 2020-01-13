package com.sqt.edu.course.service.impl;

import com.alibaba.fastjson.JSON;
import com.sqt.edu.course.constant.AccountEnum;
import com.sqt.edu.course.entity.AccUser;
import com.sqt.edu.course.entity.AccUserRealNameDTO;
import com.sqt.edu.course.mapper.AccUserMapper;
import com.sqt.edu.course.service.AccUserRealNameService;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.core.utils.RequestHelper;
import com.sqt.edu.teacher.client.TeacherFeignClient;
import com.sqt.edu.teacher.request.TeacherInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-30 17:56
 */
@Slf4j
@Service
public class AccUserRealNameServiceImpl implements AccUserRealNameService {

    @Autowired
    private AccUserMapper accUserMapper;
    @Autowired
    private TeacherFeignClient teacherFeignClient;


    @Override
    public JsonResult realName(AccUserRealNameDTO accUserRealNameDTO) {
        Long userId = RequestHelper.getUserId();
        AccUser accUser = accUserMapper.selectById(userId);
        accUser.setIdCardPath1(accUserRealNameDTO.getIdCardPath1());
        accUser.setIdCard(accUserRealNameDTO.getIdCard());
        accUser.setAuthState(AccountEnum.AccUserAuthState.AUTH_SUCCESS.getCode());
        accUserMapper.updateById(accUser);
        log.info("==========>userId:{},realName:{} 用户实名认证成功!",userId,accUser.getRealName());
        return new JsonResult();
    }

    @Override
    public JsonResult authRealName(Long userId) {
        AccUser accUser = accUserMapper.selectById(userId);
        if (null == accUser){
            log.error("==========> 用户不存在 ! userId:{}",userId);
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        if (!StringUtils.equals(accUser.getAuthState(),AccountEnum.AccUserAuthState.AUTH_SUCCESS.getCode())){
            log.warn("==========> 用户未实名认证！ userId:{}",userId);
            throw new ServiceException(ResultCode.USER_NOT_AUTH);
        }
        return new JsonResult();
    }

    @Override
    public JsonResult authNormalTeacher(TeacherInfoDTO teacherInfoDTO) {
        Long userId = RequestHelper.getUserId();
        AccUser accUser = accUserMapper.selectById(userId);
        // 避免接口攻击 再次校验用户是否实名认证
        authRealName(userId);
        teacherInfoDTO.setAccUserId(userId);
        JsonResult jsonResult = teacherFeignClient.add(teacherInfoDTO);
        log.info("==========>调用 edu-teacher 服务返回结果:{}", JSON.toJSONString(jsonResult));
        if (jsonResult.isSuccess()){
            accUser.setType(AccountEnum.AccUserType.NORMAL_TEACHER_USER.getCode());
            accUserMapper.updateById(accUser);
        }
        return jsonResult;
    }
}

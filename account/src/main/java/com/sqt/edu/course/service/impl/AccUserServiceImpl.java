package com.sqt.edu.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqt.edu.course.auth.JwtSubject;
import com.sqt.edu.course.auth.JwtTokenUtil;
import com.sqt.edu.course.constant.AccountEnum;
import com.sqt.edu.course.entity.AccUser;
import com.sqt.edu.course.mapper.AccUserMapper;
import com.sqt.edu.course.request.AccUserInfoDTO;
import com.sqt.edu.course.request.RegisterUserDTO;
import com.sqt.edu.course.request.UpdatePasswordDTO;
import com.sqt.edu.course.service.AccUserService;
import com.sqt.edu.course.service.SmsService;
import com.sqt.edu.course.utils.PasswordEncoder;
import com.sqt.edu.core.base.BaseLoginParam;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.core.utils.RequestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-16 16:41
 */
@Slf4j
@Service
public class AccUserServiceImpl implements AccUserService {

    @Autowired
    private AccUserMapper accUserMapper;
    @Autowired
    private SmsService smsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public AccUserInfoDTO create(RegisterUserDTO registerUserDTO) {
        validMsgCode(registerUserDTO);
        AccUser accUser = AccUser.builder()
                .phone(registerUserDTO.getPhone())
                .password(PasswordEncoder.encode(registerUserDTO.getPassword()))
                .authState(AccountEnum.AccUserAuthState.NOT_AUTH.getCode())
                .type(AccountEnum.AccUserType.NORMAL_USER.getCode())
                .state(AccountEnum.AccUserState.IN_USE.getCode())
                .build();
        accUser.setCreateTime(new Date());
        accUser.setUpdateTime(new Date());
        accUserMapper.insert(accUser);
        AccUserInfoDTO accUserInfoDTO = new AccUserInfoDTO();
        BeanUtils.copyProperties(accUser,accUserInfoDTO);
        return accUserInfoDTO;
    }

    @Override
    public void sendRegisterCode(String phone) {
        smsService.sendPhone(phone);
    }

    @Override
    public JsonResult loginByPassword(BaseLoginParam baseLoginParam) {
        AccUser accUser = accUserMapper.selectOne(new QueryWrapper<AccUser>().eq("phone", baseLoginParam.getPhone()
        ));
        if (null == accUser) {
            log.error("==========>用户不存在!phone:{}", baseLoginParam.getPhone());
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        if (!StringUtils.equals(accUser.getPassword(), PasswordEncoder.encode(baseLoginParam.getPassword()))) {
            log.error("==========>用户密码错误!phone:{}", baseLoginParam.getPhone());
            throw new ServiceException(ResultCode.USER_PASSWORD_ERROR);
        }
        String access_token = jwtTokenUtil.generateToken(new JwtSubject(accUser.getId(), accUser.getType()), null, null);
        Map<String, Object> loginResult = new HashMap<>();
        loginResult.put("access_token", access_token);
        loginResult.put("userId", accUser.getId());
        return new JsonResult(loginResult);
    }

    @Override
    public JsonResult updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        Long userId = RequestHelper.getUserId();
        AccUser accUser = accUserMapper.selectById(userId);
        if (!StringUtils.equals(accUser.getPassword(), PasswordEncoder.encode(updatePasswordDTO.getOldPassword()))) {
            throw new ServiceException(ResultCode.USER_PASSWORD_ERROR);
        }
        accUser.setPassword(PasswordEncoder.encode(updatePasswordDTO.getNewPassword()));
        accUserMapper.updateById(accUser);
        log.info("==========>用户(userId):{}修改密码成功!", userId);
        return new JsonResult();
    }

    /**
     * 校验 验证码
     *
     * @param registerUserDTO
     */
    private void validMsgCode(RegisterUserDTO registerUserDTO) {
    }
}

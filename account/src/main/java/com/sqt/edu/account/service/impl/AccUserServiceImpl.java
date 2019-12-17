package com.sqt.edu.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netflix.discovery.converters.Auto;
import com.sqt.edu.account.constant.AccountEnum;
import com.sqt.edu.account.entity.AccUser;
import com.sqt.edu.account.mapper.AccUserMapper;
import com.sqt.edu.account.request.RegisterUserDTO;
import com.sqt.edu.account.service.AccUserService;
import com.sqt.edu.account.service.SmsService;
import com.sqt.edu.account.utils.PasswordEncoder;
import com.sqt.edu.core.base.BaseLoginParam;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public AccUser create(RegisterUserDTO registerUserDTO) {
        validMsgCode(registerUserDTO);
        AccUser accUser = AccUser.builder()
                .phone(registerUserDTO.getPhone())
                .username(registerUserDTO.getUsername())
                .password(PasswordEncoder.encode(registerUserDTO.getPassword()))
                .sex(registerUserDTO.getSex())
                .authState(AccountEnum.AccUserAuthState.NOT_AUTH.getCode())
                .type(registerUserDTO.getType())
                .state(AccountEnum.AccUserState.IN_USE.getCode())
                .build();
        accUser.setCreateTime(new Date());
        accUser.setUpdateTime(new Date());
        accUserMapper.insert(accUser);
        return accUser;
    }

    @Override
    public void sendRegisterCode(String phone) {
        smsService.sendPhone(phone);
    }

    @Override
    public JsonResult loginByPassword(BaseLoginParam baseLoginParam) {
        AccUser accUser = accUserMapper.selectOne(new QueryWrapper<AccUser>().eq("phone", baseLoginParam.getPhone()
        ));
        if (null == accUser){
            log.error("==========>用户不存在!phone:{}",baseLoginParam.getPhone());
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        if (!StringUtils.equals(accUser.getPassword(),PasswordEncoder.encode(baseLoginParam.getPassword()))){
            log.error("==========>用户密码错误!phone:{}",baseLoginParam.getPhone());
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        return new JsonResult();
    }

    /**校验 验证码
     * @param registerUserDTO
     */
    private void validMsgCode(RegisterUserDTO registerUserDTO) {
    }
}

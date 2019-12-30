package com.sqt.edu.account.service.impl;

import com.sqt.edu.account.entity.AccUser;
import com.sqt.edu.account.entity.AccUserRealNameDTO;
import com.sqt.edu.account.mapper.AccUserMapper;
import com.sqt.edu.account.service.AccUserRealNameService;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.utils.RequestHelper;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public JsonResult realName(AccUserRealNameDTO accUserRealNameDTO) {
        Long userId = RequestHelper.getUserId();
        AccUser accUser = accUserMapper.selectById(userId);
        accUser.setIdCardPath1(accUserRealNameDTO.getIdCardPath1());
        accUser.setIdCard(accUserRealNameDTO.getIdCard());
        accUserMapper.updateById(accUser);
        return new JsonResult();
    }
}

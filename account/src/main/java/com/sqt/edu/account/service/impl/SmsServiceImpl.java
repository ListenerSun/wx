package com.sqt.edu.account.service.impl;

import com.sqt.edu.account.constant.MsgEnum;
import com.sqt.edu.account.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-17 10:11
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {


    private static final int VERIFYCODE_MAX = 9999;
    private static final int VERIFYCODE_MIN = 1000;

    @Override
    public void sendPhone(Long msgId, String phone) {

    }

    @Override
    public int sendPhone(String phone) {
        int code = getRandomCode();
        log.info("==========>手机号:{}生成的验证码为:{}",phone,code);
        return code;
    }


    private int getRandomCode() {
        Random random = new Random();
        return random.nextInt((VERIFYCODE_MAX - VERIFYCODE_MIN) + 1) + VERIFYCODE_MIN;
    }
}

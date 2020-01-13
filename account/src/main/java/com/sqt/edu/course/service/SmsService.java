package com.sqt.edu.course.service;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-17 9:52
 */
public interface SmsService {

    /**send a message
     * @param msgId
     * @param phone
     */
    void sendPhone(Long msgId, String phone);

    /**
     * no sms service
     */
    int sendPhone(String phone);
}

package com.sqt.edu.account.utils;

import com.sqt.edu.account.entity.AccUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * 密码加密工具类
 *
 * @author sqt
 * @date 19-03-13
 */
@Slf4j
public final class PasswordEncoder {



    private static final String SALT = "ListenerSun";

    public static String encode(String password) {
        return encode256(encode512(password, SALT), SALT);
    }

    public static String encode(String password, AccUser user) {
        return encode256(encode512(password, user.getId()), user.getId());
    }

    private static String encode512(String password, Object salt) {
        return DigestUtils.sha512Hex(password + salt);
    }

    private static String encode256(String password, Object salt) {
        return DigestUtils.sha256Hex(password + salt);
    }

    public static String encodeWithMd5(String str) {
        return DigestUtils.md5Hex(str);
    }
}
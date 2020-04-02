package com.sqt.edu.core.utils;

import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.constant.AuthConstants;
import com.sqt.edu.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 请求工具类
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-27 19:30
 */

@Slf4j
public class RequestHelper {

    /**
     * 获取当前请求的Request对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的Response对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getResponse();
        }
    }

    /**
     * 获取请求UserId
     *
     * @return
     */
    public static Long getUserId() {
        HttpServletRequest request = getRequest();
        return getUserId(request);
    }

    /**
     * 获取请求UserId
     *
     * @param request
     * @return
     */
    public static Long getUserId(HttpServletRequest request) {
        String userId = request.getHeader(AuthConstants.HTTP_HEADER_USERID);
        if (StringUtils.isBlank(userId)) {
            userId = request.getParameter(AuthConstants.HTTP_HEADER_USERID);
        }
        if (StringUtils.isBlank(userId)) {
            //从token中获取
            String token = request.getHeader(AuthConstants.HTTP_HEADER_TOKEN);
            if (StringUtils.isBlank(token)) {
                return -1L;
            }
            RedisHelper redisHelper = SpringContextHelper.getBean("redisHelper");
            if (redisHelper == null) {
                log.error("==========>获取RedisHelper对象失败");
                throw new ServiceException(ResultCode.CORE_SPRINGCONTEXTHELPER_ERROR);
            } else {
                //todo 设计登入时的 token 时 补充完这块代码
//                JwtSubject jwtSubject = redisHelper.getObj(CacheConstants.CACHE_USER, token, JwtSubject.class);
//                if (jwtSubject != null) {
//                    return jwtSubject.getUserId();
//                }
            }
        }
        if (StringUtils.isBlank(userId)) {
            return -1L;
        }
        Long temp = -1L;
        try {
            if (NumberUtils.isNumber(userId)) {
                temp = Long.parseLong(userId);
            }
        } catch (Exception e) {
            log.error("请求header[User-Id]字段不合法，userId={}", userId);
            log.error("==========>Exception:{}",e);
        }
        return temp;
    }

    /**
     * 获取请求的ip地址
     */
    public static String getRequestIp() {
        HttpServletRequest request = RequestHelper.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip)
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip)
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip)
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
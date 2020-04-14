package com.sqt.edu.gateway.auth;

import com.sqt.edu.common.constant.AuthConstants;
import com.sqt.edu.common.constant.CacheConstants;
import com.sqt.edu.common.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-13 17:11
 */
@Slf4j
@Service
public class AuthService {

    @Autowired
    private RedisHelper redisHelper;

    /**access_token 校验
     * @param request
     * @param response
     * @return
     */
    public boolean authToken(ServerHttpRequest request, ServerHttpResponse response) {
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AuthConstants.HTTP_HEADER_TOKEN);
        String userId = headers.getFirst(AuthConstants.HTTP_HEADER_USERID);
        if (token == null) {
            token = request.getQueryParams().getFirst(AuthConstants.HTTP_HEADER_TOKEN);
        }
        if (userId == null) {
            userId = request.getQueryParams().getFirst(AuthConstants.HTTP_HEADER_USERID);
        }

        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)) {
            log.warn("==========>缓存中access_token过期");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
        String authToken = redisHelper.getObj(userId, CacheConstants.CACHE_ACCESS_TOKEN, String.class);
        if (authToken == null || !authToken.equals(token)) {
            log.warn("==========>缓存中access_token过期");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
        return true;
    }
}

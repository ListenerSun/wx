package com.sqt.edu.core.auth;

import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Description: JWT token 工具类
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-21 19:43
 */
@Slf4j
@Component
public class JwtTokenUtil {

    /**
     * jwt的秘钥
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * 默认jwt的过期时间
     */
    @Value("${jwt.defaultExpiredDate}")
    private Long defaultExpiredDate;


    public JwtTokenUtil(String jwtSecret, Long defaultExpiredDate) {
        this.jwtSecret = jwtSecret;
        this.defaultExpiredDate = defaultExpiredDate;
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDate(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDate(String token) {
        return getClaimFromToken(token).getExpiration();
    }


    /**
     * 获取jwt接收者
     */
    public String getAudience(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaim(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取jwt的payload部分
     */
    public Claims getClaimFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            //出现异常原因是jwt解析不了的情况
            log.error("==========>解析token失败！ e:{}",e);
            throw new ServiceException(ResultCode.AUTH_TOKEN_ERROR);
        }
    }

    /**
     * 解析token是否正确(true-正确, false-错误)
     */
    public Boolean checkToken(String token) throws JwtException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * 验证token是否失效
     * true:过期   false:没过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDate(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            expiredJwtException.printStackTrace();
            log.error("获取jwt过期时间失败,message={}",expiredJwtException.getMessage());
            return true;
        }
    }

    /**
     * 生成token,根据userId和默认过期时间
     */
    public String generateToken(JwtSubject jwtSubject, Long expiredDate, Map<String, Object> claims) {
        Long tempExpired = expiredDate;
        if (expiredDate == null || expiredDate <= 0) {
            tempExpired = defaultExpiredDate;
        }
        final Date expirationDate = new Date(System.currentTimeMillis() + tempExpired * 1000);
        return generateToken(jwtSubject.toString(), expirationDate, claims);
    }

    /**
     * 生成token,根据userId和过期时间
     */
    private String generateToken(String jwtSubject, Date expiredDate, Map<String, Object> claims) {
        final Date createdDate = new Date();
        if (claims == null) {
            return Jwts.builder()
                    .setSubject(jwtSubject)
                    .setIssuedAt(createdDate)
                    .setExpiration(expiredDate)
                    .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                    .compact();
        } else {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(jwtSubject)
                    .setIssuedAt(createdDate)
                    .setExpiration(expiredDate)
                    .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                    .compact();
        }
    }
}

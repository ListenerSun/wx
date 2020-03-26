package com.sqt.edu.core.constant;

/**
 * @Description: 公共常量
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-26 18:42
 */
public final class CommonConstant {

    /**
     * 分布式锁id
     */
    public final static String REDIS_LOCK_ID = "ListenerSun";
    /**
     * 分布式锁失效时长 单位取决于分布式锁中的参数。
     */
    public final static Long REDIS_LOCK_EXPIRE_TIME = 5000L;
    /**
     * 缓存雪崩时  默认返回值
     */
    public static final String REDIS_NULL_DEFAULT = "null";
}

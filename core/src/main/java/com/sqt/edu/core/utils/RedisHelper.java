package com.sqt.edu.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 * Key生成说明
 * key=微服务标识:数据标识（业务表主键｜自定义唯一Key）
 * <p>
 * 数据标识必须唯一
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-25 22:24
 */
@Slf4j
@Component
public class RedisHelper {

    /**
     * 分布式锁
     */
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    /**
     * 这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作
     */
    private static final String SET_IF_NOT_EXIST = "NX";
    /**
     * 这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置。 EX：单位为 second ; PX:单位为 ms
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";



    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 判断缓存是否存在
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Boolean existKey(String cacheName, String key) {
        if (StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException(ResultCode.CORE_PARAM_INVALID);
        }
        return redisTemplate.hasKey(redisKey(cacheName, key).toLowerCase());
    }

    /**
     * 保存
     *
     * @param cacheName
     * @param key
     * @param t
     * @param <T>
     */
    public <T> void setObj(String cacheName, String key, T t) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException(ResultCode.CORE_PARAM_INVALID);
        }
        BoundValueOperations<String, T> valueOperations = redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t);
    }

    /**
     * 保存
     *
     * @param cacheName
     * @param key
     * @param t
     * @param <T>
     */
    public <T> void setObj(String cacheName, String key, List<T> t) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException(ResultCode.CORE_PARAM_INVALID);
        }
        BoundValueOperations<String, List<T>> valueOperations = redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t);
    }

    /**
     * 保存
     *
     * @param cacheName
     * @param key
     * @param t
     * @param timeout
     * @param <T>
     */
    public <T> void setObj(String cacheName, String key, T t, long timeout) {
        BoundValueOperations<String, T> valueOperations = redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t, timeout, TimeUnit.SECONDS);
    }

    /**
     * 保存
     *
     * @param cacheName
     * @param key
     * @param t
     * @param timeout
     * @param <T>
     */
    public <T> void setObj(String cacheName, String key, List<T> t, long timeout) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException(ResultCode.CORE_PARAM_INVALID);
        }
        BoundValueOperations<String, List<T>> valueOperations = redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存对象
     *
     * @param cacheName
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getObj(String cacheName, String key, Class<T> tClass) {
        if (StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException(ResultCode.CORE_PARAM_INVALID);
        }
        String redisKey = redisKey(cacheName, key).toLowerCase();

        boolean hasKey = redisTemplate.hasKey(redisKey);
        if (!hasKey) {
            log.error("==========>redis key={} 不存在", redisKey);
            return null;
        }
        BoundValueOperations<String, T> valueOperations = redisTemplate.boundValueOps(redisKey);
        if (tClass == String.class) {
            return valueOperations.get();
        }
        return JSON.parseObject(valueOperations.get().toString(), tClass);
    }

    /**
     * 获取缓存对象
     *
     * @param cacheName
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> getArrayObj(String cacheName, String key, Class<T> tClass) {
        if (StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException(ResultCode.CORE_PARAM_INVALID);
        }
        String redisKey = redisKey(cacheName, key).toLowerCase();

        boolean hasKey = redisTemplate.hasKey(redisKey);
        if (!hasKey) {
            log.error("==========>redis key={} 不存在", key);
            return null;
        }
        BoundValueOperations<String, List<T>> valueOperations = redisTemplate.boundValueOps(redisKey);

        return JSONArray.parseArray(valueOperations.get().toString(), tClass);
    }

    /**
     * 清空公共的缓存
     *
     * @param cacheName
     * @param redisKey
     */
    public void delRedis(String cacheName, String redisKey) {
        Set<String> keys = stringRedisTemplate.keys(redisKey(cacheName, redisKey).toLowerCase() + "*");
        redisTemplate.delete(keys);
    }

    /**
     * 自增
     *
     * @param key
     * @param liveTime
     * @return
     */
    public Long incr(String cacheName, String key, long liveTime) {
        String redisKey = redisKey(cacheName, key).toLowerCase();
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(redisKey, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        boolean in = increment == null || increment.longValue() == 0;
        if (in && liveTime > 0) {
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }

    /**
     * 对于热搜问题，每次搜索默认　对应分值加　１
     */
    private static final Double score = Double.valueOf(1);

    /**
     * 　搜索时增加对应的关键字的分值
     *
     * @param key   　改搜索对应的分类
     * @param value 搜索的关键字
     */
    public void add(String cacheName, String key, String value) {
        ZSetOperations zSet = redisTemplate.opsForZSet();
        Double scores = zSet.score(redisKey(cacheName, key).toLowerCase(), value);
        // 已经存在key加一
        if (null != scores) {
            zSet.incrementScore(redisKey(cacheName, key).toLowerCase(), value, score);
        } else {
            zSet.add(redisKey(cacheName, key).toLowerCase(), value, score);
        }
    }

    /**
     * 从大到小查找最热门的搜索关键字
     *
     * @param key   搜索对应的分类
     * @param count 关键字的数量
     * @return　对应的关键字
     */
    public List<String> reverseFindTop(String cacheName, String key, Long count) {
        ZSetOperations zSet = redisTemplate.opsForZSet();
        Set set = zSet.reverseRangeByScore(redisKey(cacheName, key).toLowerCase(), 0, Double.MAX_VALUE, 0, count);
        return new ArrayList<>(set);
    }

    /**
     * 获取key的一个value的score
     * @param key
     * @param value
     * @return
     */
    public Double getScore(String cacheName, String key, String value) {
        ZSetOperations zSet = redisTemplate.opsForZSet();
        return zSet.score(redisKey(cacheName, key).toLowerCase(), value);
    }

    /**
     * 生成缓存Key
     *
     * @param cacheName
     * @param redisKey
     * @return
     */
    private String redisKey(String cacheName, String redisKey) {
        return cacheName + "::" + redisKey;
    }

    /**
     * 存放hash
     *
     * @param cacheName
     * @param key
     * @param field
     * @param value
     * @param <T>
     */
    public <T> void hset(String cacheName, String key, String field, T value) {
        redisTemplate.opsForHash().put(redisKey(cacheName, key), field, value);
    }

    /**
     * 查询hash缓存
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> hgetAll(String cacheName, String key) {
        return redisTemplate.opsForHash().entries(redisKey(cacheName, key));
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String cacheName, String key, String field) {
        return redisTemplate.opsForHash().hasKey(redisKey(cacheName, key), field);
    }

    /**
     * hash
     *
     * @param key
     * @param map
     */
    public void hputAll(String cacheName, String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(redisKey(cacheName, key), map);
    }

    /**
     * 查询hash 值
     *
     * @param key
     * @return
     */
    public <T> List<T> hvalues(String cacheName, String key, Class c) {
        return JSON.parseArray(JSON.toJSONString(redisTemplate.opsForHash().values(redisKey(cacheName, key))), c);
    }

    /**
     * hash 删除
     *
     * @param key
     * @param field
     */
    public void hdel(String cacheName, String key, String field) {
        redisTemplate.opsForHash().delete(redisKey(cacheName, key), field);
    }

    /**
     * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
     * 对于 Redis 集群则无法使用
     *
     * 支持重复，线程安全
     *
     * @param lockKey   加锁键
     * @param clientId  加锁客户端唯一标识(采用UUID)
     * @param seconds   锁过期时间
     * @return
     */
    public Boolean tryLock(String lockKey, String clientId, long seconds){
        return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                String result = jedis.set(lockKey, clientId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds);
                if (LOCK_SUCCESS.equals(result)) {
                    log.info("==========>clientId：{} 加锁成功!",clientId);
                    return Boolean.TRUE;
                }
                log.warn("==========>clientId:{} 获取锁失败!",clientId);
                return Boolean.FALSE;
            }
        });
    }

    /**
     * 与 tryLock 相对应，用作释放锁
     *
     * @param lockKey
     * @param clientId
     * @return
     */
    public Boolean releaseLock(String lockKey, String clientId) {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKey),
                    Collections.singletonList(clientId));
            if (RELEASE_SUCCESS.equals(result)) {
                log.info("==========>clientId：{} 释放锁成功!",clientId);
                return Boolean.TRUE;
            }
            log.warn("==========>clientId：{} 释放锁失败!",clientId);
            return Boolean.FALSE;
        });
    }
}

package com.sqt.edu.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.sqt.edu.common.annotation.DS;
import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.constant.CommonConstant;
import com.sqt.edu.common.constant.ServiceConstant;
import com.sqt.edu.core.entity.TestTable;
import com.sqt.edu.core.entity.UserScore;
import com.sqt.edu.core.mapper.TestTableMapper;
import com.sqt.edu.core.mapper.UserScoreMapper;
import com.sqt.edu.core.service.TestTableService;
import com.sqt.edu.core.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-20 16:49
 */
@Slf4j
@Service
public class TestTableServiceImpl implements TestTableService {

    @Autowired
    private TestTableMapper testTableMapper;
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserScoreMapper userScoreMapper;

    @Override
    public JsonResult inteceptor() {
        TestTable testTable = new TestTable();
        testTableMapper.insert(testTable);
        return new JsonResult();
    }

    @Override
    public JsonResult webLog(TestTable testTable) {
        if (null == testTable.getId()) {
            TestTable table = TestTable.builder()
                    .name("test01")
                    .build();
            testTableMapper.insert(table);
            return new JsonResult(table);
        }
        return new JsonResult(testTableMapper.selectById(testTable.getId()));
    }

    @Async("asyncExecutor")
    @Override
    public Future<String> doTaskOne() throws InterruptedException {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成，耗时" + (end - start) + "毫秒");
    }

    @Async("asyncExecutor")
    @Override
    public Future<String> doTaskTwo() throws InterruptedException {

        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成，耗时" + (end - start) + "毫秒");
    }

    @Async("asyncExecutor")
    @Override
    public Future<String> doTaskThree() throws InterruptedException {
        log.info("开始做任务三");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成，耗时" + (end - start) + "毫秒");
    }

    @Override
    public JsonResult testTableService() {
        TestTable testTable = testTableMapper.selectById(1211471794976972809L);
        testTable.setEmail("3333");
        testTableMapper.updateById(testTable);
        return new JsonResult();
    }

    @DS("master")
    @Override
    public JsonResult createTx() {
        TestTable testTable = TestTable.builder()
                .name("wby")
                .email("123@qq.com")
                .build();
        testTableMapper.insert(testTable);
        int i = 1 / 0;
        return new JsonResult();
    }

    @DS("master")
    @Override
    public JsonResult dsMaster() {
        return new JsonResult(testTableMapper.selectById(1L));
    }

    @DS("slave")
    @Override
    public JsonResult dsSlave() {
        return new JsonResult(testTableMapper.selectById(1L));
    }

    @Override
    public JsonResult redis(String type) {
        switch (type) {
            case "1":
                return redisString();
            case "2":
                return redisZsetScore();
            case "3":
                return redisLock(type);
            default:
                return redisFindTop(type);
        }
    }

    /**
     * 测试分布式锁
     * 由于工具类的原因 只用 key:String,value:String 方式测试
     *
     * @param type
     * @return
     */
    private JsonResult redisLock(String type) {
        String jsonTestTable = redisHelper.getObj("wby", "key", String.class);
        if (null != jsonTestTable) {
            return getJsonResult(jsonTestTable);
        } else {
            String clientId = Thread.currentThread().getName();
            Boolean isLock = redisHelper.tryLock(CommonConstant.REDIS_LOCK_ID, clientId,
                    CommonConstant.REDIS_LOCK_EXPIRE_TIME);
                if (isLock) {
                    try {
                        //再次查询缓存
                        String s = redisHelper.getObj("wby", "key", String.class);
                        if (null != s) {
                            return getJsonResult(s);
                        }
                        TestTable selectOne = testTableMapper.selectByType(type);
                        if (null == selectOne) {
                            redisHelper.setObj("wby", "key", CommonConstant.REDIS_NULL_DEFAULT);
                            log.info("==========>当前线程: {} 设置了null！",Thread.currentThread().getName());
                        } else {
                            redisHelper.setObj("wby", "key", JSON.toJSONString(selectOne));
                            log.info("==========>当前线程: {} 设置了test数据！",Thread.currentThread().getName());
                        }
                        return new JsonResult(selectOne);
                    }catch (Exception e) {
                        log.error("==========>当前线程:{},获取锁后业务出现异常:{}", Thread.currentThread().getName(),e);
                    } finally {
                        redisHelper.releaseLock(CommonConstant.REDIS_LOCK_ID, clientId);
                    }
                }else {
                    redisLock(type);
                }
            }

        return null;
    }

    /**
     * 处理 查询缓存结果为 "null"时 返回结果
     *
     * @param jsonTestTable
     * @return
     */
    private JsonResult getJsonResult(String jsonTestTable) {
        if (StringUtils.equals(jsonTestTable, CommonConstant.REDIS_NULL_DEFAULT)) {
            return new JsonResult();
        }
        TestTable testTable = JSON.parseObject(jsonTestTable, TestTable.class);
        log.info("==========>当前线程:{} 在缓存中拿到了数据",Thread.currentThread().getName());
        return new JsonResult(testTable);
    }

    /**
     * 测试搜索关键词排行
     *
     * @return
     */
    private JsonResult redisFindTop(String type) {
        redisHelper.add("key", "key", type);
        List<String> stringList = redisHelper.reverseFindTop("key", "key", 10L);
        return new JsonResult(stringList);
    }

    /**
     * 存储分数排行榜
     *
     * @return
     */
    private JsonResult redisZsetScore() {
        List<UserScore> userScoreList = userScoreMapper.selectLimit100();
        //构建DefaultTypedTuple对象  设置分数(Double类型)
        Set<DefaultTypedTuple<UserScore>> typedTuples =
                userScoreList.stream().map(userScore -> new DefaultTypedTuple<UserScore>(userScore,
                        new Double(userScore.getScore()))
                ).collect(Collectors.toSet());
        //向集合中插入元素，并设置分数  真正实际场景查询在redis中查询,新增时向redis中新增
        redisTemplate.opsForZSet().add("user_score", typedTuples);
        Set<ZSetOperations.TypedTuple<UserScore>> tuples = redisTemplate.opsForZSet().rangeWithScores("user_score", 0,
                100);
        return new JsonResult(tuples);
    }

    private JsonResult redisString() {
        redisHelper.setObj(ServiceConstant.ACCOUNT_SERVICE_NAME, "account", "test");
        String account = redisHelper.getObj(ServiceConstant.ACCOUNT_SERVICE_NAME, "account", String.class);
        log.info("==========>取出的值:{}", account);
        return new JsonResult(account);
    }
}

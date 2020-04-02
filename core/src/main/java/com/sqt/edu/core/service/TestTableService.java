package com.sqt.edu.core.service;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.core.entity.TestTable;

import java.util.concurrent.Future;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-20 16:48
 */
public interface TestTableService {
    /**
     *test sql inteceptor
     * @return
     */
    JsonResult inteceptor();

    /**test AOP Log
     * @param testTable
     */
    JsonResult webLog(TestTable testTable);

    /**
     * @Asyn 测试
     * @return
     */
    Future<String> doTaskOne() throws InterruptedException;

    Future<String> doTaskTwo() throws InterruptedException;

    Future<String> doTaskThree() throws InterruptedException;

    /**乐观锁插件测试
     * @return
     */
    JsonResult testTableService();

    /** 事务 测试
     * @return
     */
    JsonResult createTx();

    /**多数据源测试
     * @param
     * @return
     */
    JsonResult dsMaster();
    JsonResult dsSlave();

    /**redis 测试
     * @param type
     * @return
     */
    JsonResult redis(String type);
}

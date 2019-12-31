package com.sqt.edu.account.service;

import com.sqt.edu.account.entity.TestTable;
import com.sqt.edu.core.base.JsonResult;

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
}

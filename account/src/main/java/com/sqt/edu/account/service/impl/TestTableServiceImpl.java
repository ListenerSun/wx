package com.sqt.edu.account.service.impl;

import com.sqt.edu.account.entity.TestTable;
import com.sqt.edu.account.mapper.TestTableMapper;
import com.sqt.edu.account.service.TestTableService;
import com.sqt.edu.core.base.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.Date;
import java.util.concurrent.Future;

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

    @Override
    public Future<String> doTaskOne() throws InterruptedException {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成，耗时" + (end - start) + "毫秒");
    }

    @Override
    public Future<String> doTaskTwo() throws InterruptedException {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成，耗时" + (end - start) + "毫秒");
    }

    @Override
    public Future<String> doTaskThree() throws InterruptedException {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成，耗时" + (end - start) + "毫秒");
    }
}

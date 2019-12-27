package com.sqt.edu.account.controller;

import com.sqt.edu.account.entity.TestTable;
import com.sqt.edu.account.service.TestTableService;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.constant.ServiceConstant;
import com.sqt.edu.core.utils.RedisHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-20 16:44
 */
@Slf4j
@Api(tags = "【Test_Table测试controller】")
@RestController
@RequestMapping("/test")
public class TestTableController {

    @Autowired
    private TestTableService testTableService;
    @Autowired
    private RedisHelper redisHelper;

    @ApiOperation(value = "t-1.1-sql拦截器测试")
    @GetMapping("/inteceptor")
    public JsonResult inteceptor(){
        return testTableService.inteceptor();
    }

    @ApiOperation(value = "t-1.2-切面日志打印测试")
    @PostMapping("/web_log")
    public JsonResult webLog(@RequestBody TestTable testTable){
        return testTableService.webLog(testTable);
    }
    @ApiOperation(value = "t-1.3-redis测试")
    @PostMapping("/redis")
    public JsonResult redis(){
        redisHelper.setObj(ServiceConstant.ACCOUNT_SERVICE_NAME,"account","test");
        String account = redisHelper.getObj(ServiceConstant.ACCOUNT_SERVICE_NAME, "account", String.class);
        log.info("==========>取出的值:{}",account);
        return new JsonResult(account);
    }

}

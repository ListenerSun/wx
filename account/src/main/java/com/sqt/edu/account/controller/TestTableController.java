package com.sqt.edu.account.controller;

import com.sqt.edu.account.entity.TestTable;
import com.sqt.edu.account.service.TestTableService;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.constant.ServiceConstant;
import com.sqt.edu.core.utils.RedisHelper;
import com.sqt.edu.core.validation.IdCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.concurrent.Future;

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

    @ApiOperation(value = "t-1.4-身份证号验证注解")
    @PostMapping("/idcard")
    public JsonResult idCardValid(@RequestBody @Valid IdCardTest idCard){
        log.info("=======> 进来了");
        return new JsonResult(idCard);
    }

    @ApiOperation(value = "t-1.5-异步执行测试")
    @GetMapping("/task")
    public String taskExecute() {
        try {
            Future<String> r1 = testTableService.doTaskOne();
            Future<String> r2 = testTableService.doTaskTwo();
            Future<String> r3 = testTableService.doTaskThree();

            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            log.info("当前线程为 {}，请求方法为 {}，请求路径为：{}", Thread.currentThread().getName(), request.getMethod(), request.getRequestURL().toString());
            while (true) {
                if (r1.isDone() && r2.isDone() && r3.isDone()) {
                    log.info("execute all tasks");
                    break;
                }
                Thread.sleep(200);
            }
            log.info("\n" + r1.get() + "\n" + r2.get() + "\n" + r3.get());
        } catch (Exception e) {
            log.error("error executing task for {}", e.getMessage());
        }

        return "ok";
    }

    @Data
    @ApiModel
    static final class IdCardTest{
        @ApiModelProperty("idCarad")
        private String idCard;

        @NotBlank(message = "不能为空!")
        @ApiModelProperty("name")
        private String name;
    }
}

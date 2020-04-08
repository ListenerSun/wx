package com.sqt.edu.teacher.client.fallback;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.exception.ServiceException;
import com.sqt.edu.teacher.client.TestFeginClient;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-02 10:11
 */
@Component
public class TestFeginClientFallBack implements TestFeginClient {
    @Override
    public JsonResult test() {
//        return new JsonResult(ResultCode.MIC_SERVICE_EXCEPTION);
        throw new ServiceException(ResultCode.MIC_SERVICE_EXCEPTION);
    }
}

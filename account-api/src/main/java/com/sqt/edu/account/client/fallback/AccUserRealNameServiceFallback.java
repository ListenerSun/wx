package com.sqt.edu.account.client.fallback;

import com.sqt.edu.account.client.AccUserRealNameServiceClient;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-31 15:06
 */
@Component
public class AccUserRealNameServiceFallback implements FallbackFactory<AccUserRealNameServiceClient> {
    @Override
    public AccUserRealNameServiceClient create(Throwable throwable) {
        return new AccUserRealNameServiceClient() {
            @Override
            public JsonResult authRealName(@NotNull Long userId) {
                throw new ServiceException(ResultCode.MIC_SERVICE_EXCEPTION);
            }
        };
    }
}

package com.sqt.edu.course.client.fallback;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.exception.ServiceException;
import com.sqt.edu.course.client.CourseFeignClient;
import com.sqt.edu.course.request.CourseDTO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-13 14:56
 */
@Component
public class CourseFeignClientFallBack implements FallbackFactory<CourseFeignClient> {
    @Override
    public CourseFeignClient create(Throwable throwable) {
        return new CourseFeignClient() {
            @Override
            public JsonResult create(@Valid CourseDTO courseDTO) {
                throw new ServiceException(ResultCode.MIC_SERVICE_EXCEPTION);
            }
        };
    }
}

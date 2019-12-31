package com.sqt.edu.teacher.client.fallback;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
import com.sqt.edu.core.exception.ServiceException;
import com.sqt.edu.teacher.client.TeacherFeignClient;
import com.sqt.edu.teacher.request.TeacherInfoDTO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-31 15:40
 */
@Component
public class TeacherFeignClientFallBack implements FallbackFactory<TeacherFeignClient> {
    @Override
    public TeacherFeignClient create(Throwable throwable) {
        return new TeacherFeignClient() {
            @Override
            public JsonResult add(@Valid TeacherInfoDTO teacherInfoDTO) {
                throw new ServiceException(ResultCode.MSG_NOT_READABLE);
            }
        };
    }
}

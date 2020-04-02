package com.sqt.edu.teacher.client.fallback;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.exception.ServiceException;
import com.sqt.edu.teacher.client.TeacherFeignClient;
import com.sqt.edu.teacher.entity.TeacherInfo;
import com.sqt.edu.teacher.request.TeacherInfoDTO;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-31 15:40
 */
@Component
public class TeacherFeignClientFallBack implements TeacherFeignClient {

    @Override
    public JsonResult add(@Valid TeacherInfoDTO teacherInfoDTO) {
        throw new ServiceException(ResultCode.MIC_SERVICE_EXCEPTION);
    }

    @Override
    public JsonResult<TeacherInfo> getTeacherInfoByAccUserId(Long accUserId) {
        throw new ServiceException(ResultCode.MIC_SERVICE_EXCEPTION);
    }
}

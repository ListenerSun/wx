package com.sqt.edu.student.service;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.student.dto.request.QueryStuRegisterInfoDTO;
import com.sqt.edu.student.dto.request.StuRegisterInfoDTO;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-13 23:29
 */
public interface StuRegisterInfoService {

    /** add
     * @param stuRegisterInfoDTO
     * @return
     */
    JsonResult add(StuRegisterInfoDTO stuRegisterInfoDTO);

    /**
     * @param queryStuRegisterInfoDTO
     * @return
     */
    JsonResult queryStuRegisterInfo(QueryStuRegisterInfoDTO queryStuRegisterInfoDTO);
}

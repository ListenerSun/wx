package com.sqt.edu.student.service;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.student.dto.request.QueryStuRegisterInfoDTO;
import com.sqt.edu.student.dto.request.StuRegisterInfoDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-13 23:29
 */
public interface StuRegisterInfoService {

    /**
     * add
     *
     * @param stuRegisterInfoDTO
     * @return
     */
    JsonResult add(StuRegisterInfoDTO stuRegisterInfoDTO);

    /**
     * query one
     *
     * @param phone
     * @param year
     * @return
     */
    JsonResult queryStuRegisterInfo(String phone, Integer year);

    /**
     * list
     *
     * @param queryStuRegisterInfoDTO
     * @return
     */
    JsonResult queryStuRegisterInfoList(QueryStuRegisterInfoDTO queryStuRegisterInfoDTO);

    /**
     * 导出
     *
     * @param ids
     * @return
     */
    void exportExcel(List<Long> ids);
}

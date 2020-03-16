package com.sqt.edu.student.service;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.student.dto.request.ClassInfoDTO;
import com.sqt.edu.student.dto.request.ClassOrderDTO;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-12 21:33
 */
public interface ClassInfoService {

    /** add
     * @param classInfoDTO
     * @return
     */
    JsonResult add(ClassInfoDTO classInfoDTO);


    /** update
     * @param classInfoDTO
     * @return
     */
    JsonResult update(ClassInfoDTO classInfoDTO);

    /** delete
     * @param classInfoId
     * @return
     */
    JsonResult delete(Long classInfoId);
}

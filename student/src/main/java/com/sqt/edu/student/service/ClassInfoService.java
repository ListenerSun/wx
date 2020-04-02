package com.sqt.edu.student.service;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.student.dto.request.ClassInfoDTO;
import com.sqt.edu.student.dto.request.QueryClassInfoDTO;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-12 21:33
 */
public interface ClassInfoService {

    /**********************************************后台管理端需要的接口******************************/

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

    /**list
     * @return
     */
    JsonResult list(QueryClassInfoDTO queryClassInfoDTO);

    /**发布招生
     * @param classInfoId
     * @return
     */
    JsonResult enrollClass(Long classInfoId);

    /**********************************************用户端需要的接口******************************/

    /**获取正在招生的补课班级列表
     * @return
     */
    JsonResult enrollClassInfoList();

}

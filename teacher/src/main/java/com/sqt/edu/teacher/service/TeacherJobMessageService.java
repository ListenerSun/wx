package com.sqt.edu.teacher.service;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.teacher.request.TeacherJobMessageDTO;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date:  Created in 2020-01-03 19:27
 */
public interface TeacherJobMessageService {

    /**发布一条教教信息
     * @param teacherJobMessageDTO
     * @return
     */
    JsonResult create(TeacherJobMessageDTO teacherJobMessageDTO);

    /**更新
     * @param teacherJobMessageDTO
     * @return
     */
    JsonResult update(TeacherJobMessageDTO teacherJobMessageDTO);

    /**删除
     * @param teacherJobMessageId
     * @return
     */
    JsonResult delete(Long teacherJobMessageId);

    /** 根据 用户id 查询 发布家教信息列表
     * @param userId
     * @return
     */
    JsonResult selectByTeacherId(Long userId);
}

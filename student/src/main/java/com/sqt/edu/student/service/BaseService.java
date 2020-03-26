package com.sqt.edu.student.service;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.student.mapper.MyBaseMapper;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-22 18:21
 */
public abstract class BaseService<M extends MyBaseMapper> {

    /**导出
  * @param ids
     * @return
     */
    public abstract JsonResult exportExcel(List<Long> ids);
}

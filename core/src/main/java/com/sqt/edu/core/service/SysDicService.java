package com.sqt.edu.core.service;

import com.sqt.edu.core.entity.SysDicData;

import java.util.List;

/**
 * @Description: 字典公共服务接口
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-18 22:27
 */
public interface SysDicService {

    /**根据 SysDicType 的 Code 查询字典列表
     * @param dicTypeCode
     * @return
     */
    List<SysDicData> queryByDicTypeCode(String dicTypeCode);
}

package com.sqt.edu.common.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.common.api.entity.SysDicType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-18 22:30
 */
public interface SysDicTypeMapper extends BaseMapper<SysDicType> {

    @Select(value = "select * from sys_dic_type")
    List<SysDicType> queryAllDicType();
}

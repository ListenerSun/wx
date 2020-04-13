package com.sqt.edu.common.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.common.api.entity.SysDicData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-18 22:27
 */
public interface SysDicDataMapper extends BaseMapper<SysDicData> {

    /**
     * @param dicTypeCode
     * @return
     */
    @Select(value = "select * from sys_dic_data where dic_type_code = #{dicTypeCode,jdbcType=VARCHAR}")
    List<SysDicData> queryByDicType(@Param("dicTypeCode") String dicTypeCode);
}

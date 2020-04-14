package com.sqt.edu.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.core.entity.TestTable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-20 16:43
 */
public interface TestTableMapper extends BaseMapper<TestTable> {

    /**
     * 根据type 查找
     *
     * @param type
     * @return
     */
    @Select(value = "select * from test_table where type =#{type,jdbcType=VARCHAR}")
    TestTable selectByType(@Param("type") String type);

    @Select(value = " select * from test_table")
    List<Map<String, Integer>> jdbcType();
}

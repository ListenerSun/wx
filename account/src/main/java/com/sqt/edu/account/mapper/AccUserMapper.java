package com.sqt.edu.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.account.entity.AccUser;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-16 17:01
 */
public interface AccUserMapper extends BaseMapper<AccUser> {

    /**return the lasted id save or update
     * @return
     */
    @Select(value = "select LAST_INSERT_ID()")
    Long selectLastId();
}

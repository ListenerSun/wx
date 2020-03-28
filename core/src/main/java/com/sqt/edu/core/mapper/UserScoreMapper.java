package com.sqt.edu.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqt.edu.core.entity.UserScore;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-26 10:59
 */
public interface UserScoreMapper extends BaseMapper<UserScore> {

    @Select(value = "select * from user_score limit 100")
    List<UserScore> selectLimit100();
}

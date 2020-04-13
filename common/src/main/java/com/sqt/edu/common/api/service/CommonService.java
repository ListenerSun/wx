package com.sqt.edu.common.api.service;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.constant.CacheConstants;
import com.sqt.edu.common.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-13 9:16
 */
@Service
@Slf4j
public class CommonService {

    @Autowired
    private SysDicService sysDicService;
    @Autowired
    private RedisHelper redisHelper;


    public JsonResult getAllDic() {
        Map<String, Object> dicMap = redisHelper.hgetAll(CacheConstants.DIC_CACHE, CacheConstants.CACHE_KEY_DEFAULT);
        return new JsonResult(dicMap);
    }
}

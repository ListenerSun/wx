package com.sqt.edu.core.config;

import com.sqt.edu.common.api.entity.SysDicData;
import com.sqt.edu.common.api.entity.SysDicType;
import com.sqt.edu.common.api.mapper.SysDicDataMapper;
import com.sqt.edu.common.api.mapper.SysDicTypeMapper;
import com.sqt.edu.common.constant.CacheConstants;
import com.sqt.edu.common.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 字典初始化 缓存 配置
 *
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-13 9:39
 */
@Slf4j
@Configuration
public class InitConfiguration implements InitializingBean {

    @Autowired
    private SysDicTypeMapper sysDicTypeMapper;
    @Autowired
    private SysDicDataMapper sysDicDataMapper;
    @Autowired
    private RedisHelper redisHelper;

    @Override
    public void afterPropertiesSet() throws Exception {
        SysDicInit();

    }

    /**
     * 字典缓存初始化
     */
    private void SysDicInit() {
        log.info("==========>字典缓存初始化开始");
        List<SysDicType> sysDicTypeList = sysDicTypeMapper.queryAllDicType();
        if (null != sysDicTypeList && sysDicTypeList.size() != 0) {
            sysDicTypeList.forEach(e -> {
                List<SysDicData> sysDicDataList = sysDicDataMapper.queryByDicType(e.getCode());
                if (!redisHelper.hexists(CacheConstants.DIC_CACHE,CacheConstants.CACHE_KEY_DEFAULT,e.getCode())){
                    redisHelper.hset(CacheConstants.DIC_CACHE,CacheConstants.CACHE_KEY_DEFAULT,e.getCode(),sysDicDataList);
                }
            });
        }
        log.info("==========>字典初始化完毕");
    }
}

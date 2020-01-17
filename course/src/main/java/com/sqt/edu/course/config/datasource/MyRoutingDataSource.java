package com.sqt.edu.course.config.datasource;

import com.sqt.edu.core.holder.DbContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义RoutingDataSource数据源类
 *
 * @author ListenerSun(男, 未婚) 微信:810548252
 * @date 19-3-19
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getCurrentDsStr();
    }
}
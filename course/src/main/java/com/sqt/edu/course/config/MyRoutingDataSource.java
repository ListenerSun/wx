package com.sqt.edu.course.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义RoutingDataSource数据源类
 *
 * @author cyj
 * @date 19-3-19
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getCurrentDsStr();
    }
}
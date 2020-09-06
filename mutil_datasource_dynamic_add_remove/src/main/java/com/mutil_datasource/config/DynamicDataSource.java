package com.mutil_datasource.config;

import com.mutil_datasource.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 * @auther Stiles-JKY
 * @date 2020/9/6-17:24
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Map<Object,Object> backupTargetDataSource;

    public DynamicDataSource(DataSource defaultDataSource,Map<Object,Object> targetDataSource) {
        this.backupTargetDataSource = targetDataSource;
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(backupTargetDataSource);
        super.afterPropertiesSet();//负责解析成可用的目标数据源
    }

    public void addNewDataSource(String key,DataSource dataSource) {
        this.backupTargetDataSource.put(key,dataSource);
        super.setTargetDataSources(this.backupTargetDataSource);
        super.afterPropertiesSet();//负责解析成可用的目标数据源
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }
}

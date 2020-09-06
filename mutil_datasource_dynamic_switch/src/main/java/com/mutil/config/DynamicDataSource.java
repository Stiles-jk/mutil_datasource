package com.mutil.config;

import com.mutil.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-14:34
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    //用于决定当前使用的数据源类型；即路由策略
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("determineCurrentLookupKey 被调用了");
        return DynamicDataSourceContextHolder.getContextKey();
    }

}

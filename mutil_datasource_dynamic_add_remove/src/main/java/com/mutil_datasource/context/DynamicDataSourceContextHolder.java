package com.mutil_datasource.context;

import com.mutil_datasource.constants.DataSourceConstants;

/**
 * 数据源切换类
 *
 * @auther Stiles-JKY
 * @date 2020/9/6-17:30
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> DATASOURCE_CONTEXT_KEY_HOLDER = new ThreadLocal<>();

    //获取当前线程中设置的数据源
    public static String getContextKey() {
        String key = DATASOURCE_CONTEXT_KEY_HOLDER.get();
        return key == null ? DataSourceConstants.DS_KEY_MASTER : key;
    }

    //设置当前线程中的数据源
    public static void setContextKey(String key) {
        System.out.println("set current DataSource: " + key);
        DATASOURCE_CONTEXT_KEY_HOLDER.set(key);
    }

    /**
     * 移除当前数据源名称
     */
    public static void removeContextKey() {
        DATASOURCE_CONTEXT_KEY_HOLDER.remove();
    }
}

package com.mutil.context;

import com.mutil.constants.DataSourceConstants;

/**
 * 动态数据源名称上下文处理
 *
 * @auther Stiles-JKY
 * @date 2020/9/6-14:45
 */
public class DynamicDataSourceContextHolder {
    /**
     * 动态数据源名称上下文
     */
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_KEY_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     */
    public static void setContextKey(String key) {
        System.out.println("switch data source: " + key);
        DATASOURCE_CONTEXT_KEY_HOLDER.set(key);
    }

    /**
     * 获取数据源名称
     *
     * @return 数据源名称
     */
    public static String getContextKey() {
        String key = DATASOURCE_CONTEXT_KEY_HOLDER.get();
        return key == null ? DataSourceConstants.DS_KEY_MASTER : key;
    }

    /**
     * 移除当前数据源名称
     */
    public static void removeContextKey() {
        DATASOURCE_CONTEXT_KEY_HOLDER.remove();
    }
}

package com.mutil_datasource.proxy;

import com.mutil_datasource.context.DynamicDataSourceContextHolder;
import com.mutil_datasource.entity.DbInfo;
import com.mutil_datasource.utils.DataSourceUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-19:27
 */
public class DataSourceMethodProxy implements InvocationHandler {
    //数据源key
    private String dataSourceKey;
    //数据库连接信息
    private DbInfo dbInfo;
    //代理目标对象
    private Object targetObject;

    public DataSourceMethodProxy(String dataSourceKey, DbInfo dbInfo, Object targetObject) {
        this.dataSourceKey = dataSourceKey;
        this.dbInfo = dbInfo;
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        //切换数据源
        DataSourceUtil.addDataSourceToDynamic(dataSourceKey, dbInfo);
        DynamicDataSourceContextHolder.setContextKey(dataSourceKey);
        //调用方法
        Object result = method.invoke(targetObject, args);
        DynamicDataSourceContextHolder.removeContextKey();
        return result;
    }

    /**
     * 创建代理
     *
     * @param targetObject
     * @param dataSourceKey
     * @param dbInfo
     * @return
     * @throws Exception
     */
    public static Object createProxyInstance(Object targetObject, String dataSourceKey, DbInfo dbInfo) throws Exception {
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
                , targetObject.getClass().getInterfaces(), new DataSourceMethodProxy(dataSourceKey, dbInfo, targetObject));
    }

}

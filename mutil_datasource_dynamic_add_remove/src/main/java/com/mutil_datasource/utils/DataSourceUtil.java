package com.mutil_datasource.utils;

import com.mutil_datasource.config.DynamicDataSource;
import com.mutil_datasource.entity.DbInfo;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * 添加数据源时，经过 Spring 上下文获取动态数据源的 bean，而后添加。
 *
 * @auther Stiles-JKY
 * @date 2020/9/6-18:27
 */
public class DataSourceUtil {

    //建立新的数据源
    public static DataSource makeNewDataSource(DbInfo dbInfo) {
        String url = "jdbc:mysql://" + dbInfo.getIp() + ":" + dbInfo.getPort() + "/" + dbInfo.getDbName() +
                "?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String driveClassName =
                StringUtils.isEmpty(dbInfo.getDriveClassName()) ?
                        "com.mysql.cj.jdbc.Driver" : dbInfo.getDriveClassName();
        DataSource newDataSource = DataSourceBuilder
                .create()
                .url(url)
                .driverClassName(driveClassName)
                .username(dbInfo.getUsername())
                .password(dbInfo.getPassword())
                .build();

        return newDataSource;
    }

    //添加数据源到动态数据源中
    public static void addDataSourceToDynamic(String key, DataSource dataSource) {
        DynamicDataSource dynamicDataSource = SpringContextHolder
                .getApplicationContext()
                .getBean(DynamicDataSource.class);
        dynamicDataSource.addNewDataSource(key, dataSource);
    }

    //根据数据库链接信息添加数据源到动态源中
    public static void addDataSourceToDynamic(String key, DbInfo dbInfo) {
        DataSource dataSource = makeNewDataSource(dbInfo);
        addDataSourceToDynamic(key, dataSource);
    }
}

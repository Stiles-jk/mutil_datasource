package com.mutil.config;

import com.mutil.constants.DataSourceConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取配置文件中的所有datasource，并分别实例化
 *
 * @auther Stiles-JKY
 * @date 2020/9/6-13:52
 */
@Configuration
@PropertySource("classpath:config/jdbc.properties")//读取jdbc配置文件中的信息
@MapperScan(basePackages = "com.mutil.mapper")//使用 MapperScan 指定包，自动注入相应的 mapper 类
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })//排除 DataSourceAutoConfiguration 的自动配置，否则 会出现The dependencies of some of the beans in the application context form a cycle的错误。
public class DynamicDataSourceConfig {

    @Bean(DataSourceConstants.DS_KEY_MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(DataSourceConstants.DS_KEY_SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary//使用注解 Primary 优先从动态数据源中获取
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceConstants.DS_KEY_MASTER, masterDataSource());
        dataSourceMap.put(DataSourceConstants.DS_KEY_SLAVE, slaveDataSource());
        //设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);//将当前动态数据源覆盖AbstractRoutingDataSource中管理的map
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());//设置默认数据源
        return dynamicDataSource;
    }
}

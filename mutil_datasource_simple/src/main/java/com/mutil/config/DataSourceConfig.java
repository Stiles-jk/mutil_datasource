package com.mutil.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @auther Stiles-JKY
 * @date 2020/9/3-23:13
 */
@Configuration
@PropertySource("classpath:config/jdbc.properties")
public class DataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")//指定主从配置前缀
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }
}

package com.mutil_datasource.config;

import com.mutil_datasource.constants.DataSourceConstants;
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
 * 读取配置文件中定义的数据源
 *
 * @auther Stiles-JKY
 * @date 2020/9/6-17:19
 */
@Configuration
@PropertySource("classpath:config/jdbc.properties")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)//禁止springboot自动配置数据源
@MapperScan(basePackages = "com.mutil_datasource.mapper")
public class DynamicDataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceConstants.DS_KEY_MASTER,masterDataSource());
        dataSourceMap.put(DataSourceConstants.DS_KEY_SLAVE,slaveDataSource());
        //有参构造函数
        return new DynamicDataSource(masterDataSource(), dataSourceMap);
    }
}

package com.mutil.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @auther Stiles-JKY
 * @date 2020/9/3-23:20
 */
@Configuration
@MapperScan(basePackages = "com.mutil.mapper.master",sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterMybatisConfig {

    @Bean
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("master")DataSource dataSource) throws Exception {
        //设置数据源
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        //mapper 的 xml 文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String locationPattern = "classpath:/mapper/master/*.xml";
        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));
        //数据库对应的pojo
        String typeAliasesPackage = "com.mutil.pojo.master";
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return mybatisSqlSessionFactoryBean.getObject();

    }
}

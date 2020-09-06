package com.mutil_datasource.controller;

import com.mutil_datasource.context.DynamicDataSourceContextHolder;
import com.mutil_datasource.entity.DbInfo;
import com.mutil_datasource.entity.TestUser;
import com.mutil_datasource.mapper.TableMapper;
import com.mutil_datasource.mapper.TestUserMapper;
import com.mutil_datasource.proxy.DataSourceMethodProxy;
import com.mutil_datasource.service.TestUserService;
import com.mutil_datasource.utils.DataSourceUtil;
import com.mutil_datasource.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-18:47
 */
@RestController
@RequestMapping("/user")
public class TestUserController {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private TestUserService testUserService;

    @Autowired
    private TableMapper tableMapper;

    @GetMapping("/table")
    public Object findWithDbInfo(DbInfo dbInfo) {
        //数据源 key
        String newDSKey = System.currentTimeMillis() + "";
        //添加数据源
        DataSourceUtil.addDataSourceToDynamic(newDSKey,dbInfo);
        DynamicDataSourceContextHolder.setContextKey(newDSKey);
        //查询表信息
        List<Map<String, Object>> tables = tableMapper.selectTableList();
        DynamicDataSourceContextHolder.removeContextKey();
        return ResponseResult.success(tables);
    }

    @GetMapping("/table_proxy")
    public Object findWithDbInfoProxy(DbInfo dbInfo) throws Exception {
        //数据源 key
        String newDSKey = System.currentTimeMillis() + "";
        //代理添加
        TableMapper tableMapperProxy = (TableMapper)DataSourceMethodProxy.createProxyInstance(tableMapper, newDSKey, dbInfo);
        List<Map<String, Object>> tables = tableMapperProxy.selectTableList();
        return ResponseResult.success(tables);
    }
}

package com.mutil.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mutil.config.DynamicDataSource;
import com.mutil.constants.DataSourceConstants;
import com.mutil.context.DynamicDataSourceContextHolder;
import com.mutil.entity.TestUser;
import com.mutil.mapper.TestUserMapper;
import com.mutil.service.TestUserService;
import com.mutil.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-14:52
 */
@RestController
@RequestMapping("/user")
public class TestUserController {
    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private TestUserService testUserService;

    //测试查询全部
    @GetMapping("/listAll")
    public Object listAll() {
        int initSize = 2;
        Map<String,Object> result = new HashMap<>(initSize);
        //默认使用master数据源
        QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();
        List<TestUser> resultData = testUserMapper.selectAll(queryWrapper.isNotNull("name"));
        result.put(DataSourceConstants.DS_KEY_MASTER,resultData);

        //切换数据源
        DynamicDataSourceContextHolder.setContextKey(DataSourceConstants.DS_KEY_SLAVE);
        List<TestUser> resultDataSlave = testUserMapper.selectList(null);
        result.put(DataSourceConstants.DS_KEY_SLAVE,resultDataSlave);
        //恢复数据源
        DynamicDataSourceContextHolder.removeContextKey();
        //返回数据
        return  ResponseResult.success(result);
    }

    //测试aop
    @GetMapping("/listAllAop")
    public Object listAllAop() {
        int initSize = 2;
        Map<String, Object> result = new HashMap<>(initSize);
        //使用默认数据源
        List<TestUser> masterUser = this.testUserService.getMasterUser();
        result.put(DataSourceConstants.DS_KEY_MASTER,masterUser);
        result.put("new line","-----------------------------------");
        //使用slave数据源
        List<TestUser> slaveUser = testUserService.getSlaveUser();
        result.put(DataSourceConstants.DS_KEY_SLAVE, slaveUser);
        //返回数据
        return ResponseResult.success(result);
    }
}

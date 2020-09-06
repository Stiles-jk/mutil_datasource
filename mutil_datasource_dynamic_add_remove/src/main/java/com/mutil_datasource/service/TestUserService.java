package com.mutil_datasource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mutil_datasource.annotation.DS;
import com.mutil_datasource.constants.DataSourceConstants;
import com.mutil_datasource.entity.TestUser;
import com.mutil_datasource.mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-18:49
 */
@Service
public class TestUserService {
    @Autowired
    private TestUserMapper testUserMapper;


    /**
     * 查询master库User
     * @return
     */
    @DS(DataSourceConstants.DS_KEY_MASTER)
    public List<TestUser> getMasterUser(){
        QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();
        List<TestUser> resultData = testUserMapper.selectAll(queryWrapper.isNotNull("name"));
        return resultData;
    }

    /**
     * 查询slave库User
     * @return
     */
    @DS(DataSourceConstants.DS_KEY_SLAVE)
    public List<TestUser> getSlaveUser(){
        return testUserMapper.selectList(null);
    }

}
package com.mutil.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mutil.annotaion.DS;
import com.mutil.constants.DataSourceConstants;
import com.mutil.entity.TestUser;
import com.mutil.mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-16:38
 */
@Service
public class TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @DS()//使用默认数据源
    public List<TestUser> getMasterUser() {
        QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();
        return testUserMapper.selectAll(queryWrapper.isNotNull("name"));
    }

    @DS(DataSourceConstants.DS_KEY_SLAVE)
    public List<TestUser> getSlaveUser() {
        return testUserMapper.selectList(null);
    }

}

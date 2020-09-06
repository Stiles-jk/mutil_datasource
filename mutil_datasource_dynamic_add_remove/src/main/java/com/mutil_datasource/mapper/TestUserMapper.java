package com.mutil_datasource.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mutil_datasource.entity.TestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-14:56
 */
@Repository
public interface TestUserMapper extends BaseMapper<TestUser> {
    /**
     * 自定义查询
     * @param wrapper 条件构造器
     * @return
     */
    List<TestUser> selectAll(@Param(Constants.WRAPPER) Wrapper<TestUser> wrapper);
}

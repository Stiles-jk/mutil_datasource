package com.mutil.mapper.slave;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mutil.pojo.slave.SlaveTestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther Stiles-JKY
 * @date 2020/9/5-22:47
 */
@Repository
public interface SlaveTestUserMapper extends BaseMapper<SlaveTestUser> {
    List<SlaveTestUser> selectAll(@Param(Constants.WRAPPER) Wrapper<SlaveTestUser> wrapper);
}

package com.mutil.mapper.master;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mutil.pojo.master.MasterTestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther Stiles-JKY
 * @date 2020/9/5-22:37
 */
@Repository
public interface MasterTestUserMapper extends BaseMapper<MasterTestUser> {
    List<MasterTestUser> selectAll(@Param(Constants.WRAPPER) Wrapper<MasterTestUser> wrapper);
}

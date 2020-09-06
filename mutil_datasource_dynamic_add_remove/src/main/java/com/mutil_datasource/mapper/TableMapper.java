package com.mutil_datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mutil_datasource.entity.TestUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-18:46
 */
@Repository
public interface TableMapper extends BaseMapper<TestUser> {
    /**
     * 查询表信息
     * @return
     */
    @Select("select table_name, table_comment, create_time, update_time " +
            " from information_schema.tables " +
            " where table_schema = (select database())")
    List<Map<String,Object>> selectTableList();
}

package com.mutil.service;

import com.mutil.mapper.master.MasterTestUserMapper;
import com.mutil.mapper.slave.SlaveTestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-11:09
 */
@Service
public class FindService {

    @Autowired
    private MasterTestUserMapper masterTestUserMapper;
    @Autowired
    private SlaveTestUserMapper slaveTestUserMapper;

    public Object find() {
        return null;
    }
}

package com.example.computeserver.dao;

import com.example.computeserver.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    /**
     * 模拟用户查询
     * @param name
     * @return
     */
    @Override
    public SysUser findByUserName(String name) {
        SysUser sysUser = new SysUser();
        sysUser.setName("compute");
        sysUser.setPassword("password123");
        sysUser.setId("U123");
        LOGGER.info("***************** find user by username ******************");
        return sysUser;
    }
}

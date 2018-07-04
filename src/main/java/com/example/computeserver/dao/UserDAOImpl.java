package com.example.computeserver.dao;

import com.example.computeserver.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
    SysUser sysUser1 = new SysUser();
    SysUser sysUser2 = new SysUser();
    {
        sysUser1.setName("compute");
        sysUser1.setPassword("password123");
        sysUser1.setId("U123");

        sysUser2.setName("compute2");
        sysUser2.setPassword("password123");
        sysUser2.setId("U456");
    }

    /**
     * 模拟用户查询
     *
     * @param name
     * @return
     */
    @Override
    public SysUser findByUserName(String name) {

        LOGGER.info("***************** find user by username ******************");
        return sysUser1;
    }
}

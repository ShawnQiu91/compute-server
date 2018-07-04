package com.example.computeserver.dao;

import com.example.computeserver.entity.SysUser;

public interface UserDAO {
    SysUser findByUserName(String name);
}

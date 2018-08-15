package com.example.computeserver.service;

import com.example.computeserver.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
    int addUser(User user);

    PageInfo<User> findAllUser(int pageNum, int pageSize);
}

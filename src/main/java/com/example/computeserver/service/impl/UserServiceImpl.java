package com.example.computeserver.service.impl;

import com.example.computeserver.mapper.UserMapper;
import com.example.computeserver.model.User;
import com.example.computeserver.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    /*
   * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
   * 需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
   * pageNum 开始页数
   * pageSize 每页显示的数据条数
   * */
    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法实现物理分页。
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAllUser();
        PageInfo<User> page = new PageInfo<>(list);
        return page;
    }
}

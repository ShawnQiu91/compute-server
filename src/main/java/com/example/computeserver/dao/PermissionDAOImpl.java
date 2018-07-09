package com.example.computeserver.dao;

import com.example.computeserver.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PermissionDAOImpl implements PermissionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionDAOImpl.class);

    //模拟权限
    Permission permission1 = new Permission();
    Permission permission2 = new Permission();
    Permission permission3 = new Permission();
    Permission permission4 = new Permission();
    {
        permission1.setCode("P001");
        permission1.setName("API001");
        permission1.setUrl("/v1/number");

        permission2.setCode("P002");
        permission2.setName("API002");
        permission2.setUrl("/v1/hello");

        permission3.setCode("P003");
        permission3.setName("API003");
        permission3.setUrl("/v1/profile");

        permission4.setCode("P004");
        permission4.setName("API004");
        permission4.setUrl("/v1/hello-feign");
    }
    /**
     * 模拟查询全部权限
     * @return
     */
    @Override
    public List<Permission> findAll() {
        List permissionList = new ArrayList<>();

        permissionList.add(permission1);
        permissionList.add(permission2);
        permissionList.add(permission3);
        permissionList.add(permission4);

        LOGGER.info("***************** find all permission ******************");
        return permissionList;
    }

    /**
     * 模拟权限查询
     * 生产环境中，应该有独立的用户权限关联表
     * @param userId
     * @return
     */
    @Override
    public List<Permission> findByUserId(String userId) {
        List permissionList = new ArrayList<>();

        //permissionList.add(permission1);
        permissionList.add(permission2);
        LOGGER.info("***************** find user permission by userId ******************");
        return permissionList;
    }
}

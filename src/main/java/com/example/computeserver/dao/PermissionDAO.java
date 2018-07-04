package com.example.computeserver.dao;

import com.example.computeserver.entity.Permission;

import java.util.List;

public interface PermissionDAO {
    List<Permission> findAll();
    List<Permission> findByUserId(String userId);
}

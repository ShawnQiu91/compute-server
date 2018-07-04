package com.example.computeserver.security.permission;

import com.example.computeserver.dao.PermissionDAO;
import com.example.computeserver.dao.UserDAO;
import com.example.computeserver.entity.Permission;
import com.example.computeserver.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取用户信息service
 * 将用户信息封装到security自己的实体中
 */
@Service
public class CustomUserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserService.class);
    @Autowired
    UserDAO userDao;
    @Autowired
    PermissionDAO permissionDao;

    public UserDetails loadUserByUsername(String username) {
        LOGGER.info("***************** username : " + username + " ******************");
        SysUser user = userDao.findByUserName(username);
        if (user != null) {
            List<Permission> permissions = permissionDao.findByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getName(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}

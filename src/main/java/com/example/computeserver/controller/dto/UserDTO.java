package com.example.computeserver.controller.dto;

public class UserDTO {
    private String Key = "b8ab2f3a-b15a-4e77-ba31-ec2316abde2a";
    // 是否已经过期
    private Boolean IsExpired = false;
    // 获取会话的注册时间，即开始时间
    private String RegistryTime = "0001-01-01T00:00:00";
    // 获取或设置会话的最后活动时间
    private String LastActiveTime = "2017 - 05 - 22T15:14:40.7758469+08:00";
    // 是否为永久标示
    private Boolean IsPersistent = false;
    // 过期时间
    private int ExpireSlide = 5;
    // 用户的登录IP
    private String IP = null;
    // 用户的登录密码(可配置为不显示)
    private String PassWord;
    // 用户ID
    private String Identity;
    // 用户账号
    private String UserCode;
    // 用户姓名
    private String UserName;
    private String token;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Boolean getIsExpired() {
        return IsExpired;
    }

    public void setIsExpired(Boolean expired) {
        IsExpired = expired;
    }

    public String getRegistryTime() {
        return RegistryTime;
    }

    public void setRegistryTime(String registryTime) {
        RegistryTime = registryTime;
    }

    public String getLastActiveTime() {
        return LastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        LastActiveTime = lastActiveTime;
    }

    public Boolean getPersistent() {
        return IsPersistent;
    }

    public void setPersistent(Boolean persistent) {
        IsPersistent = persistent;
    }

    public int getExpireSlide() {
        return ExpireSlide;
    }

    public void setExpireSlide(int expireSlide) {
        ExpireSlide = expireSlide;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

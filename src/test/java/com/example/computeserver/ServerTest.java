package com.example.computeserver;

import org.springframework.util.AntPathMatcher;

import java.util.Map;

public class ServerTest {
    /**
     * 构造方法可以调用实例方法
     */
    public ServerTest() {
        AntPathMatcherTest();
    }

    /**
     * RestfulAPI形式的匹配
     */
    public void AntPathMatcherTest() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/v1/{uuid}/{id}", "/v1/123456/1234"));
        Map<String, String> result = matcher.extractUriTemplateVariables("{uuid}", "123456");
        System.out.println(result);
    }

    public static void main(String[] args) {
        new ServerTest();
    }
}

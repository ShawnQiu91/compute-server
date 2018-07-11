package com.example.computeserver;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

import java.util.Map;

public class ServerTest {
    @Test
    public void AntPathMatcherTest() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/v1/{uuid}/{id}", "/v1/123456/1234"));
        Map<String, String> result = matcher.extractUriTemplateVariables("{uuid}", "123456");
        System.out.println(result);
    }
}

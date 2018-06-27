package com.example.computeserver.feignclient;

import org.springframework.stereotype.Component;

/**
 * hystrix,使用feign后的容错处理类
 */
@Component
public class FeignHelloClientFallback implements HelloClient {
    @Override
    public String hello() {
        return "Feign fallback : request server isn't working";
    }
}

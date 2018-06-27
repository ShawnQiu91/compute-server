package com.example.computeserver.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "compute-service")
public interface HelloClient {

    @GetMapping("/v1/hello")
    String hello();
}
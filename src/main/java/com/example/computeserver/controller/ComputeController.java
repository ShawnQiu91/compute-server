package com.example.computeserver.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping
@RefreshScope
public class ComputeController {
    @Value("${profile}")
    private String profile;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取一个随机整数
     * @return
     */
    @GetMapping("/v1/number")
    @ApiOperation(value = "获取一个随机整数", notes = "", produces = "application/json;charset=UTF-8")
    public int geneNumber(){
        return new Random().nextInt();
    }

    @GetMapping("/v1/profile")
    @ApiOperation(value = "获取一个配置参数", notes = "", produces = "application/json;charset=UTF-8")
    public String viewProfile(){
        return profile;
    }

    /**
     * 问好
     * @return
     */
    @GetMapping("/v1/hello")
    @ApiOperation(value = "问好", notes = "", produces = "application/json;charset=UTF-8")
    public String hello(){
        return restTemplate.getForEntity("http://compute-service/v1/hello", String.class).getBody();
    }
}

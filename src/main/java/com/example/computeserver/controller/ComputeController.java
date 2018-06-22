package com.example.computeserver.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping
@RefreshScope
public class ComputeController {
    @Value("${profile}")
    private String profile;

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
}

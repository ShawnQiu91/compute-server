
package com.example.computeserver.controller;

import com.example.computeserver.entity.SysUser;
import com.example.computeserver.feignclient.HelloClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping
@RefreshScope
public class ComputeController {
    @Value("${profile}")
    private String profile;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HelloClient helloClient;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取一个随机整数
     *
     * @return
     */
    @GetMapping("/v1/number/{num}")
    @ApiOperation(value = "获取输入的数", notes = "", produces = "application/json;charset=UTF-8")
    public int geneNumber(@PathVariable(name = "num") int num) {
        return num;
    }

    @GetMapping(value = "/v1/number", params = "action=random")
    @ApiOperation(value = "获取一个随机整数", notes = "", produces = "application/json;charset=UTF-8")
    public int geneRandomNumber() {
        return new Random().nextInt();
    }

    @GetMapping("/v1/profile")
    @ApiOperation(value = "获取一个配置参数", notes = "", produces = "application/json;charset=UTF-8")
    public String viewProfile() {
        return profile;
    }

    /**
     * 问好
     *
     * @return
     */
    @GetMapping("/v1/hello")
    @ApiOperation(value = "问好", notes = "", produces = "application/json;charset=UTF-8")
    public String hello() {
        return restTemplate.getForEntity("http://compute-service/v1/hello", String.class).getBody();
    }

    //@HystrixCommand(fallbackMethod = "failFast") 当使用feign时，不使用此注解
    @GetMapping("/v1/hello-feign")
    @ApiOperation(value = "使用feign问好", notes = "", produces = "application/json;charset=UTF-8")
    public String helloFeign() {
        return helloClient.hello();
    }

    @GetMapping("/v1/hello-redis")
    public String helloRedis() {
        String redisValue = "redis test : ";
        redisTemplate.opsForValue().set("redisValue",redisValue);
        String remoteStr = restTemplate.getForEntity("http://compute-service/v1/hello", String.class).getBody();
        Object obj = redisTemplate.opsForValue().get("redisValue");
        return obj.toString() + remoteStr;
    }

    @RequestMapping(value = "/login", params = "action=submit", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) throws IOException {
        SysUser sysUser = new SysUser();
        sysUser.setPassword(password);
        sysUser.setName(username);
        request.getSession(true).setAttribute("SESSION_NAME", sysUser);
        return "login test : login success!";
    }

    /**
     * 快速失败返回
     * 使用hystrix容错时使用
     *
     * @return
     */
    public String failFast() {
        return "Failfast : request server isn't working";
    }
}


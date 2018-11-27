package com.example.computeserver.controller;

import com.example.computeserver.controller.dto.UserDTO;
import com.example.computeserver.model.User;
import com.example.computeserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


    @GetMapping(value = "/users/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    @GetMapping(value = "/api/tokenapi/GetUser")
    @ResponseBody
    public UserDTO getUser(@RequestParam("token") String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setToken(token);
        userDTO.setPassWord("123456");
        userDTO.setIsExpired(false);
        userDTO.setUserCode("misa");
        return userDTO;
    }
}

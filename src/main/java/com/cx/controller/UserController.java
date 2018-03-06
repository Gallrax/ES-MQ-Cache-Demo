package com.cx.controller;

import com.cx.entity.User;
import com.cx.repository.es.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Date: Created on 2018/3/5
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/findAll/{currentPage}/{size}")
    @ResponseBody
    public Page<User> findAll(@PathVariable("currentPage") @RequestParam(defaultValue = "1") Integer currentPage,
                              @PathVariable("size") @RequestParam(defaultValue = "3") Integer size) {
        PageRequest pageRequest = new PageRequest(currentPage, size);
        Page<User> page = userRepository.findAll(pageRequest);
        return page;
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public User findById(@PathVariable("id") String id) {
        User user = userRepository.findOne(id);
        return user;
    }
}

package com.cx.controller;

import com.cx.annotation.SysLog;
import com.cx.entity.User;
import com.cx.repository.jpa.UserRepository;
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

    @SysLog
    @RequestMapping("/findAll/{currentPage}/{size}")
    public Page<User> findAll(@PathVariable(value = "currentPage") Integer currentPage,
                              @PathVariable(value = "size") Integer size) {
        PageRequest pageRequest = new PageRequest(currentPage, size);
        Page<User> page = userRepository.findAll(pageRequest);
        return page;
    }

    @SysLog
    @RequestMapping("/find/{id}")
    public User findById(@PathVariable("id") String id) {
        User user = userRepository.findById(id);
        return user;
    }
}

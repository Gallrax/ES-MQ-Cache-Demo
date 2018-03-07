package com.cx.controller;

import com.cx.entity.Logger;
import com.cx.repository.es.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Date: Created on 2018/3/6
 * @Version: 1.0
 */
@RestController
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private LoggerRepository loggerRepository;

    @RequestMapping("/findAll/{currentPage}/{size}")
    public Page<Logger> findAll(@PathVariable("currentPage") @RequestParam(defaultValue = "1") Integer currentPage,
                                @PathVariable("size") @RequestParam(defaultValue = "3") Integer size) {
        PageRequest pageRequest = new PageRequest(currentPage, size);
        Page<Logger> page = loggerRepository.findAll(pageRequest);
        return page;
    }

}

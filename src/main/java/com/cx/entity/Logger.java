package com.cx.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @Description:
 * @Date: Created on 2018/3/6
 * @Version: 1.0
 */
@Data
@Entity(name = "cx_logger")
public class Logger {

    private String id;
    private String host;//域名
    private String ip;//ip
    private String method;//执行方法
    private String params;
    private String result;//执行结果
    private Long time;//执行时长
}

package com.cx.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;

/**
 * @Description:
 * @Date: Created on 2018/3/6
 * @Version: 1.0
 */
@Data
@Document(indexName = "es-mq-cache-demo", type = "logger")
public class Logger {

    private String id;
    private String url;//域名
    private String ip;//ip
    private String method;//执行方法
    private String params;
    private String result;//执行结果
    private Long time;//执行时长
}

package com.cx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description:
 * @Date: Created on 2018/3/5
 * @Version: 1.0
 */
@Data
@Entity(name = "cx_depart")
public class Depart {

    @Id
    private String id;
    private String name;
    private String info;
    private Date insertTime;//JPA自动对驼峰命名属性对应数据库下划线insert_time

    @OneToOne(fetch = FetchType.EAGER)//不使用懒加载
    @JoinColumn(name = "uid")
    private User user;
}

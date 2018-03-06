package com.cx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description:
 * @Date: Created on 2018/3/5
 * @Version: 1.0
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "cx_user")
public class User {

    @Id
    private String id;
    private String name;
    private Integer sex;
    private String phone;
    private Date insertTime;//JPA自动对驼峰命名属性对应数据库下划线insert_time

}

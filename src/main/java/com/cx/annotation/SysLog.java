package com.cx.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Date: Created on 2018/3/7
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
}

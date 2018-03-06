package com.cx.repository.jpa;

import com.cx.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Date: Created on 2018/3/6
 * @Version: 1.0
 */
public interface LoggerRepository extends JpaRepository<Logger, String>{
}

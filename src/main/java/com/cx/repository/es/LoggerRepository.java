package com.cx.repository.es;

import com.cx.entity.Logger;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Date: Created on 2018/3/6
 * @Version: 1.0
 */
public interface LoggerRepository extends ElasticsearchRepository<Logger, String>{
}

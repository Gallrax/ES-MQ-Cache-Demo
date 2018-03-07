package com.cx.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.cx.entity.Logger;
import com.cx.repository.es.LoggerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date: Created on 2018/3/6
 * @Version: 1.0
 */
@Slf4j
@Component
public class ActiveMQConsumer {

    @Autowired
    private LoggerRepository loggerRepository;

    @JmsListener(destination = "es-mq-cache-demo-logger")
    public void receive(String message) {
        Logger logger = JSONObject.parseObject(message, Logger.class);
        log.info(" ActiveMQConsumer receive : " + message);
        loggerRepository.save(logger);
    }
}

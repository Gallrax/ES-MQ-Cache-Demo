package com.cx.mq.provider;

import com.alibaba.fastjson.JSONObject;
import com.cx.entity.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date: Created on 2018/3/6
 * @Version: 1.0
 */
@Slf4j
@Component
public class ActiveMQProvider {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendLogger(Logger logger) {
        log.info(" es-mq-cache-demo-logger send : " + logger);
        String message = JSONObject.toJSONString(logger);
        jmsTemplate.convertAndSend("es-mq-cache-demo-logger", message);
    }
}

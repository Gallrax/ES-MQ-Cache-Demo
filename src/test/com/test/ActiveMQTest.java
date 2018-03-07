package com.test;

import com.Application;
import com.cx.entity.Logger;
import com.cx.mq.consumer.ActiveMQConsumer;
import com.cx.mq.provider.ActiveMQProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Description:
 * @Date: Created on 2018/3/7
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ActiveMQTest {

    @Autowired
    private ActiveMQProvider activeMQProvider;
    @Autowired
    private ActiveMQConsumer activeMQConsumer;

    @Test
    public void test01() throws IOException {
        System.in.read();
    }

    @Test
    public void test02() {
        activeMQProvider.sendLogger(new Logger());
    }

}

package com.spring.cloud.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhang.suxing
 * @date 2020/3/27 22:58
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * AmqpAdmin   mq系统管理功能插件
     */

    private String routingKey = "atguigu";

    private String exchange = "exchange.direct";

    private String queueName = "atguigu";

    //java默认序列化的消息
    @Test
    public void test() {
        Map<String, String> map = new HashMap<>(2);
        map.put("name", "suxing");
        rabbitTemplate.convertAndSend(exchange, routingKey, map);
    }

    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert(queueName);
        System.out.println(o.getClass().getName());
        System.out.println(o);
    }
}

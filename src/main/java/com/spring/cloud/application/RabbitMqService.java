package com.spring.cloud.application;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhang.suxing
 * @date 2020/3/28 8:30
 **/
@Service
public class RabbitMqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "atguigu")
    public void listenMessage(Map<String, String> map) {
        System.out.println("监听到消息" + map);
    }
}

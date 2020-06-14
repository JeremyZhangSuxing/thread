package com.spring.cloud.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.plugin2.message.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    private AmqpAdmin amqpAdmin;
    /**
     * binding 交换机和 queue之间的关联关系  一个绑定就是基于路由键将exchange和 queue 连接起来的路由规则。
     * exchange 和 queue 可以是n:n
     */
    private String routingKey = "atguigu";
    /**
     * 交换机 接收publisher的消息并且将消息路由给queue
     */
    private String exchange = "exchange.direct";
    /**
     * 消息的容器 消息会存在队列里直到被取走 一条消息可以被发送到多个队列
     */
    private String queueName = "atguigu";
    /**
     * 消息 消息head 和消息content 组成
     * content是非透明的
     * head routingKey delivery_model priority 信息
     */
    private Message message;

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
        System.out.println(Objects.requireNonNull(o).getClass().getName());
        System.out.println(o);
    }

    @Test
    public void createMq() {
        Exchange exchange = new DirectExchange("admin.direct", true, false, null);
        amqpAdmin.declareExchange(exchange);
        System.out.println("交换机创建成功");
        Queue queue = new Queue("dev_queue", true);
//        queue.isExclusive()
        amqpAdmin.declareQueue(new Queue("admin.test"));
        System.out.println("队列创建成功");
        amqpAdmin.declareBinding(new Binding("admin.test", Binding.DestinationType.QUEUE, "admin.direct", "amq.test", null));
        System.out.println("绑定关系创建成功");

    }
}

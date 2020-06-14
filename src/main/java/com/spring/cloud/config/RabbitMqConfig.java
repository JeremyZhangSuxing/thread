package com.spring.cloud.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhang.suxing
 * @date 2020/3/28 8:15
 **/
@Configuration
public class RabbitMqConfig {

    /**
     * 默认为jdk的序列化  需要配置转成jackSon格式
     */
    @Bean
    public MessageConverter buildJsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

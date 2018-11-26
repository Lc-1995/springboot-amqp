package com.sakura.springbootamqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Sakura
 * @Description: 自定义RabbitMQ配置
 * @Date: 2018/11/23 17:22
 */
@Configuration
public class MyRabbitMQConfig {

    /**
     *
     * @Description: 默认发送的消息是序列化后的,自定义发送Json数据
     * @auther: Sakura
     * @date: 2018/11/23 17:30
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

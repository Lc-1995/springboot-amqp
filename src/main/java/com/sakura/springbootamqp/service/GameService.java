package com.sakura.springbootamqp.service;

import com.sakura.springbootamqp.pojo.Game;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/26 10:32
 */
@Service
public class GameService {

    /**
     * @Description: 使用对象接收消息
     * @auther: Sakura
     * @date: 2018/11/26 10:47
     */
    @RabbitListener(queues = "sakura")
    public void receive(Game game) {
        System.out.println("接收的数据:" + game);
    }

    /**
     * @Description: 使用Message接收头消息
     * @auther: Sakura
     * @date: 2018/11/26 10:47
     */
    @RabbitListener(queues = "sakura.news")
    public void receive02(Message message) {
        System.out.println(message.getBody());
        System.out.println("======");
        System.out.println(message.getMessageProperties());
    }
}

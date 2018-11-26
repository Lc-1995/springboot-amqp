package com.sakura.springbootamqp;

import com.sakura.springbootamqp.pojo.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;  // Amqp管理

    /**
     * @Description: 代码创建MQ
     * @auther: Sakura
     * @date: 2018/11/26 11:15
     */
    @Test
    public void createMQ() {
        // 创建Exchange
        amqpAdmin.declareExchange(new DirectExchange("exchange.create"));
        // 创建Queue
        amqpAdmin.declareQueue(new Queue("queue.create", true));
        // 绑定Exchange和Queue
        amqpAdmin.declareBinding(new Binding("queue.create", Binding.DestinationType.QUEUE, "exchange.create", "*.create", null));
    }

    @Test
    public void contextLoads() {
    }

    /**
     * @Description: 点对点发送
     * @auther: Sakura
     * @date: 2018/11/23 17:35
     */
    @Test
    public void testDirectSend() {
        // 这种发送方式需要自己构造Message
        /*rabbitTemplate.send(exchange,routingKey,message);*/

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一个消息");
        map.put("data", Arrays.asList("hello", 333, false));
        // 可以直接传入对象作为消息体
        rabbitTemplate.convertAndSend("exchanges.direct", "sakura", map);
    }

    /**
     * @Description: 广播发送
     * @auther: Sakura
     * @date: 2018/11/23 17:36
     */
    @Test
    public void testFanoutSend() {
        rabbitTemplate.convertAndSend("exchanges.fanout", "", new Game("《魔兽争霸》", 265D));
    }

    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("sakura");
        System.out.println("接收的类型:" + o.getClass());
        System.out.println("接收的数据:" + o);
    }
}

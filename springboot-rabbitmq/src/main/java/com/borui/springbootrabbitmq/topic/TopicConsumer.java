package com.borui.springbootrabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @class: com.borui.springbootrabbitmq.topic.TopicConsumer
 * @description: topic动态路由
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/21 16:05
 */

@Component
public class TopicConsumer {
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,   //创建临时队列
            key = {"user.*", "user.#"},
            exchange = @Exchange(name = "topics", type = "topic")
    )})
    public void receive1(String message) {
        System.out.println("消费者-1："+message);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,   //创建临时队列
            key = {"user.*"},
            exchange = @Exchange(name = "topics", type = "topic")
    )})
    public void receive2(String message) {
        System.out.println("消费者-2："+message);
    }
}

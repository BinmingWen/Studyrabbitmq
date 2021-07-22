package com.borui.springbootrabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @class: com.borui.springbootrabbitmq.direct.DirectConsumer
 * @description:
 * @author: 温明彬 direct模型消息
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/21 15:49
 */

@Component
public class DirectConsumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,   //创建临时队列
            key = {"error","info"},
            exchange = @Exchange(name = "directs",type = "direct")
    ))
    public void receive1(String message) {
        System.out.println("消费者-1："+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,   //创建临时队列
            key = {"error"},
            exchange = @Exchange(name = "directs",type = "direct")
    ))
    public void receive2(String message) {
        System.out.println("消费者-2："+message);
    }

}

package com.borui.springbootrabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @class: com.borui.springbootrabbitmq.fanout.FanoutConsumer
 * @description: fanout广播模型
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/21 15:31
 */

@Component
public class FanoutConsumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "logs", type = "fanout")

    ))
    public void receive1(String message) {
        System.out.println("消费者-1："+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,  //创建临时队列
            exchange = @Exchange(name = "logs", type = "fanout")    //创建交换机

    ))
    public void receive2(String message) {
        System.out.println("消费者-2："+message);
    }

}

package com.borui.springbootrabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @class: com.borui.springbootrabbitmq.hello.HelloConsumer
 * @description: hello 模式的消费者类型
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/21 15:12
 */

@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {
    @RabbitHandler
    public void receive1(String message) {
        System.out.println("hello message="+message);
    }

}

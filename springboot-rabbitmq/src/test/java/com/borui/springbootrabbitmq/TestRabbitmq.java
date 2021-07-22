package com.borui.springbootrabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @class: com.borui.springbootrabbitmq.TestRabbitmq
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/21 15:07
 */

@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitmq {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello","hello world");
    }
    @Test
    public void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","hello work【"+i+"】");
        }
    }
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs","","这是广播模型");
    }

    @Test
    public void testDirect() {
        rabbitTemplate.convertAndSend("directs","error","error 日志信息");
    }
    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("topics","user.add","这是topic动态路由模型");
    }

}

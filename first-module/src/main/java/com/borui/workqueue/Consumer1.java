package com.borui.workqueue;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @class: com.borui.workqueue.Consumer1
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/19 17:57
 */


public class Consumer1 {
    public static void main(String[] args) throws IOException {
        System.out.println("消费者1正在启动。。。。。。。");
        Connection connection = RabbitmqUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
                //参数1：确认信息队列中的信息
                //参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}

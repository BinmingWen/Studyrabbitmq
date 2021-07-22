package com.borui.topic;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @class: com.borui.topic.Consumer1
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/21 9:43
 */


public class Consumer1 {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();

        //绑定消息队列
        channel.exchangeDeclare("topics","topic");
        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,"topics","user.*");

        //消费信息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1："+new String(body));
            }
        });

    }
}

package com.borui.direct;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @class: com.borui.direct.Consumer1
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/20 18:28
 */


public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        //指定交换机和模式
        channel.exchangeDeclare("logs_direct","direct");
        String queue = channel.queueDeclare().getQueue();
        //把交换机和临时队列绑定在一起
        channel.queueBind(queue,"logs_direct","error");
        //消费队列
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1："+new String(body));
            }
        });

    }
}

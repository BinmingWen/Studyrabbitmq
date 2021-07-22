package com.borui.fanout;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @class: com.borui.fanout.Consumer2
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/20 10:52
 */


public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs","fanout");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"logs","");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2："+new String(body));
            }
        });
    }
}

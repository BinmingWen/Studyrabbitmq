package com.borui.workqueue;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @class: com.borui.workqueue.Consumer2
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/19 17:58
 */


public class Consumer2 {
    public static void main(String[] args) throws IOException {
        System.out.println("消费者2正在启动。。。。。。。");
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者2："+new String(body));
            }
        });

    }
}

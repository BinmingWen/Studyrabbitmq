package com.borui.helloword;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.*;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @class: com.borui.rabbitmq.Consumer
 * @description: 消息队列消费者
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/19 11:39
 */


public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",true,false,false,null);
        //参数
        //参数1 消费的队列名称
        //参数2 开始消息的自动确认机制
        //参数3 消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                String str = new String(body);
                System.out.println(str);
            }
        });
        RabbitmqUtils.closeConnectionAndChanel(channel,connection);
    }
}

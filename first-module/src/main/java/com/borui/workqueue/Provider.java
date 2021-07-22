package com.borui.workqueue;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

/**
 * @class: com.borui.workqueue.Provider
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/19 17:57
 */


public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","work",null,(i+"work queue method").getBytes());
        }
        RabbitmqUtils.closeConnectionAndChanel(channel,connection);

    }
}

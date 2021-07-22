package com.borui.fanout;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @class: com.borui.fanout.Provider
 * @description: fanout方式队列
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/20 10:34
 */


public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        //创建交换机
        channel.exchangeDeclare("logs","fanout");
        //发布消息
        channel.basicPublish("logs","",null,"fanout Queue message".getBytes());
        RabbitmqUtils.closeConnectionAndChanel(channel,connection);

    }

}

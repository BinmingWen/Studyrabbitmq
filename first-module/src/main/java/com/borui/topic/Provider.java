package com.borui.topic;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @class: com.borui.topic.Provider
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/21 9:39
 */


public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitmqUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机以及交换机的类型
        channel.exchangeDeclare("topics","topic");

        //发布消息
        String routeKey = "user.add.update";
        channel.basicPublish("topics",routeKey,null,("这个是topic动态路由模型，routekey = "+routeKey).getBytes());

        //关闭连接资源
        RabbitmqUtils.closeConnectionAndChanel(channel,connection);
    }
}

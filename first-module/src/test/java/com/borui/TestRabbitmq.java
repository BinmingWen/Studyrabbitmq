package com.borui;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @class: com.borui.TestRabbitmq
 * @description: rabbitmq测试
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/19 11:01
 */


public class TestRabbitmq {
    @Test
    public void testFirstModule() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.119.119.29");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("root");
        connectionFactory.setVirtualHost("ems");
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //设置通道的值
        //参数1 队列名称 参数2 是否序列化 参数3 是否为独占 参数4 是否消费完后自动删除 参数5 额外参数
        channel.queueDeclare("hello",false,false,false,null);
        //参数1交换机名称 参数2 队列名称 参数3 传递参数额外设置 参数4 传递消息内容
        channel.basicPublish("","hello",null,"Hello World".getBytes());
        channel.close();
        connection.close();
    }
}

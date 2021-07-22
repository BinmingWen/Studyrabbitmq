package com.borui.helloword;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @class: com.borui.rabbitmq.Provider
 * @description: 消息提供者
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/19 11:38
 */


public class Provider {
    @Test
    public void testFirstModule() throws IOException, TimeoutException {
        //创建连接工厂
        Connection connection = RabbitmqUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //设置通道的值
        //参数1 队列名称
        // 参数2 是否序列化,保存到硬盘
        // 参数3 是否为独占
        // 参数4 是否消费完后自动删除
        // 参数5 额外参数
        channel.queueDeclare("hello",true,false,false,null);
        //参数1交换机名称 参数2 队列名称 参数3 传递参数额外设置 参数4 传递消息内容
        //MessageProperties.PERSISTENT_TEXT_PLAIN 对消息队列中的内容永久序列化
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"Hello World".getBytes());
        RabbitmqUtils.closeConnectionAndChanel(channel,connection);
    }
}

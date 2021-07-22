package com.borui.direct;

import com.borui.utils.RabbitmqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @class: com.borui.direct.Provider
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/20 18:18
 */


public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接通道
        Connection connection = RabbitmqUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明交换机 交换机的名称和交换机的类型
        channel.exchangeDeclare("logs_direct","direct");
        String key = "info";
        String str = "这是direct模型发布基于route key: "+key+"发布的消息";
        channel.basicPublish("logs_direct",key,null,("这是direct模型发布基于route key: ["+key+"]发布的消息").getBytes());
        RabbitmqUtils.closeConnectionAndChanel(channel,connection);


    }
}

package com.borui.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @class: com.borui.utils.RabbitmqUtils
 * @description:
 * @author: 温明彬
 * @company: 广州博瑞信息技术股份有限公司
 * @create: 2021/7/19 12:00
 */


public class RabbitmqUtils {
    private static ConnectionFactory connectionFactory;
    static{
        //创建连接工厂
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.119.119.29");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("Tom");
        connectionFactory.setPassword("root");
        connectionFactory.setVirtualHost("/Tom");
    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChanel(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

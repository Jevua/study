package com.jevua.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SenderApi {
    public static void main(String[] args) throws JMSException {
        //连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
        //连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //会话——是否开启事务
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //目的地
        Destination destination = session.createQueue("myQueue");

        //消息生产者
        MessageProducer messageProducer = session.createProducer(destination);

        //消息
        TextMessage message = session.createTextMessage("this is a msg");

        messageProducer.send(message);

        //关闭连接
        session.close();
        connection.close();
    }
}

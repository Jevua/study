package com.jevua.activemq.demo;



import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerApi {
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

        //消费者
        MessageConsumer consumer = session.createConsumer(destination);

//        TextMessage message = (TextMessage) consumer.receive();
//        System.out.println(message);

        //消息监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage msg = (TextMessage) message;
                try {
                    System.out.println(msg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

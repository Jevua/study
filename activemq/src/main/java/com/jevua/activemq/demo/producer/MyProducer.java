package com.jevua.activemq.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

@Component
public class MyProducer {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "myQueue")
    Destination queue;

    @Resource(name = "myTopic")
    Destination topic;

    public void send() {
        jmsMessagingTemplate.convertAndSend(queue, "this first msg");
        jmsMessagingTemplate.convertAndSend(queue, "this second msg");

        jmsMessagingTemplate.convertAndSend(topic, "this is a msg");

    }
}

package com.jevua.activemq.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ThirdConsumer {

    @JmsListener(destination = "test.topic", containerFactory = "jmsTopicListenerContainerFactory")
    public void process(String msg) {
        System.out.println("third consumer:" + msg);
    }
}

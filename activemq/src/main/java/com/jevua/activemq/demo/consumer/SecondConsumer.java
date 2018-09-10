package com.jevua.activemq.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SecondConsumer {

    @JmsListener(destination = "test.queue")
    public void process(String msg) {
        System.out.println("second consumer:" + msg);
    }
}

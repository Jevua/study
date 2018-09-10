package com.jevua.activemq.demo;

import com.jevua.activemq.demo.producer.MyProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqApplicationTests {

    @Autowired
    MyProducer myProducer;

    @Test
    public void contextLoads() {
        myProducer.send();
    }

}

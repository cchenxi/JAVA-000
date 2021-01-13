package io.github.cchenxi.w13;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringKafkaDemoApplicationTests {

    @Autowired
    private MessageProducer messageProducer;

    @Value("${kafka.topic}")
    private String topic;

    @Test
    public void testProducer() {
        for (int i = 0; i < 100; i++) {
            String message = "message " + i;
            System.out.println("send " + message);
            messageProducer.send(topic, message);
        }
    }
}

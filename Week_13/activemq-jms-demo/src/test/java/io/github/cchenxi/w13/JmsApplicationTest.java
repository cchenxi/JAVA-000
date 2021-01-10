package io.github.cchenxi.w13;

import io.github.cchenxi.w13.queue.QueueConsumer;
import io.github.cchenxi.w13.queue.QueueProducer;
import io.github.cchenxi.w13.topic.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test
 * Date: 2021-01-10
 *
 * @author chenxi
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JmsApplicationTest {
    @Autowired
    private QueueProducer queueProducer;

    @Value("${activemq.queue.name}")
    private String queueName;

    @Autowired
    private TopicProducer topicProducer;

    @Value("${activemq.topic.name}")
    private String topicName;


    @Test
    public void testQueue() {
        for (int i = 0; i < 100; i++) {
            queueProducer.send(queueName, "[queue] hello " + i);
        }
    }

    @Test
    public void testTopic() {
        for (int i = 0; i < 100; i++) {
            topicProducer.send(topicName, "[topic] hello " + i);
        }
    }
}

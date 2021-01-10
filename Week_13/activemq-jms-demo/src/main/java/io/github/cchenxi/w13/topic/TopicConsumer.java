package io.github.cchenxi.w13.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * Topic 消费者
 * Date: 2021-01-10
 *
 * @author chenxi
 */
@Component
public class TopicConsumer {
    @JmsListener(destination = "${activemq.topic.name}", containerFactory = "myFactory")
    public void receive(Message message) {
        System.out.println("consumer_1 receive topic message : " + message);
    }
}

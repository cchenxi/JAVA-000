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
public class TopicConsumer2 {
    @JmsListener(destination = "${activemq.topic.name}", containerFactory = "myFactory")
    public void receive(Message message) {
        System.out.println("consumer_2 receive topic message : " + message);
    }
}

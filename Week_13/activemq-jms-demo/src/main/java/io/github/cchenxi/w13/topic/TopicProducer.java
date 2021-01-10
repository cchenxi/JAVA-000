package io.github.cchenxi.w13.topic;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Topic 生产者
 * Date: 2021-01-10
 *
 * @author chenxi
 */
@Component
public class TopicProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String topic, String messageText) {
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.send(topic, session -> {
            System.out.println("send topic message : " + messageText);
            ActiveMQTextMessage message = new ActiveMQTextMessage();
            message.setText(messageText);
            return message;
        });
    }
}

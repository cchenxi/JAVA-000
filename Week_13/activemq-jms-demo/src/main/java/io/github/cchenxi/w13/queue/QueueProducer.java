package io.github.cchenxi.w13.queue;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 * Date: 2021-01-10
 *
 * @author chenxi
 */
@Component
public class QueueProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String queue, String messageText) {
        jmsTemplate.send(queue, session -> {
            System.out.println("send queue message : " + messageText);
            ActiveMQTextMessage message = new ActiveMQTextMessage();
            message.setText(messageText);
            return message;
        });
    }
}

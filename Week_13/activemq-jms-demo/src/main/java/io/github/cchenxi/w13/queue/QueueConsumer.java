package io.github.cchenxi.w13.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * 消息消费者
 * Date: 2021-01-10
 *
 * @author chenxi
 */
@Component
public class QueueConsumer {

    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(Message message) {
        System.out.println("receive queue message : " + message);
    }
}

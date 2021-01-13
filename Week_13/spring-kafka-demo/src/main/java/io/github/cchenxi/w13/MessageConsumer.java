package io.github.cchenxi.w13;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费
 * Date: 2021-01-10
 *
 * @author chenxi
 */
@Component
public class MessageConsumer {

    @KafkaListener(topics = "test-32", groupId = "cg-1")
    public void consume(String message) {
        System.out.println("receive " + message);
    }
}

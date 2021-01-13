package io.github.cchenxi.w13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产
 * Date: 2021-01-10
 *
 * @author chenxi
 */
@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String messageText) {
        kafkaTemplate.send(topic, messageText);
    }
}

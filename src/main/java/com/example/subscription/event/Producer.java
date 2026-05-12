package com.example.subscription.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String key, String event) {
        log.info("Sending event: {} with key: {} to topic: {}", event, key, topic);
        kafkaTemplate.send(topic, key, event);
    }
}

package com.enterprise.marketplace.workflowservice.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.enterprise.marketplace.workflowservice.constants.WorkflowKafkaTopics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "marketplace.kafka",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true)
public class WorkflowKafkaEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publish(String topic, String key, String payload, Map<String, String> headers) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, payload);
        if (headers != null) {
            headers.forEach((k, v) -> record.headers().add(k, v.getBytes()));
        }
        kafkaTemplate.send(record);
        log.info("Published Kafka event topic={} key={}", topic, key);
    }

    public void publishDeadLetter(String key, String payload, Map<String, String> headers) {
        publish(WorkflowKafkaTopics.DEAD_LETTER, key, payload, headers);
    }
}

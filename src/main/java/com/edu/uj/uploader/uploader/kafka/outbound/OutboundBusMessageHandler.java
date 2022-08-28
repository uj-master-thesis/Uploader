package com.edu.uj.uploader.uploader.kafka.outbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Instant;

@Slf4j
public class OutboundBusMessageHandler implements OutboundMessageHandler {
    private final ObjectMapper objectMapper;
    private final MessageProducer messageProducer;
    private final String topic;

    public OutboundBusMessageHandler(ObjectMapper objectMapper, MessageProducer messageProducer, String topic) {
        this.objectMapper = objectMapper;
        this.messageProducer = messageProducer;
        this.topic = topic;
    }

    @Override
    public void handle(CommonOutboundMessage message) {
        if (null != messageProducer) {
            try {
                String payload = objectMapper.writeValueAsString(message);
                String type = message.getMessageKey();
                ProducerRecord<String, String> producerMessage = createProducerMessage(type, payload);
                sendMessage(producerMessage);
            } catch (JsonProcessingException e) {
                log.error("Failed to serialize a message {}", message);
                throw new RuntimeException("Failed to serialize message", e);
            }
        } else {
            log.error("Missing (null) message to send");
        }
    }

    private ProducerRecord<String, String> createProducerMessage(String key, String payload) {
        return new ProducerRecord<>(topic, null, Instant.now().toEpochMilli(), key, payload);
    }

    private void sendMessage(ProducerRecord<String, String> message) {
        try {
            messageProducer.send(message);
        } catch (Exception e) {
            //TODO: make own exception
            throw new RuntimeException("Failed to send message", e);
        }
    }

    @Override
    public void close() {
        messageProducer.close();
    }

    @Override
    public void flush() {
        messageProducer.flush();
    }
}

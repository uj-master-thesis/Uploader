package com.edu.uj.uploader.uploader.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BUFFER_MEMORY_CONFIG;

@Component
public class KafkaProducerFactory {

    public Producer<String, String> createKafkaProducer(ProducerConfiguration producerConfiguration) {
        return new KafkaProducer<>(createProducerConfiguration(producerConfiguration), new StringSerializer(), new StringSerializer());
    }

    private Properties createProducerConfiguration(ProducerConfiguration producerConfiguration) {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, producerConfiguration.getServer());
        properties.put(BATCH_SIZE_CONFIG, producerConfiguration.getBatchSize());
        properties.put(BUFFER_MEMORY_CONFIG, producerConfiguration.getBufferMemory());
        return properties;
    }
}

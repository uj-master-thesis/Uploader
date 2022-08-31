package com.edu.uj.uploader.uploader.kafka;

import com.edu.uj.uploader.uploader.kafka.outbound.MessageProducer;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundBusMessageHandler;
import com.edu.uj.uploader.uploader.utils.ApplicationStop;
import com.edu.uj.uploader.uploader.utils.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class NewsBusBeans {

    @Bean("newsBusProducer")
    Producer<String, String> newsBusProducer(KafkaProducerFactory kafkaProducerFactory, NewsBusProducerConfiguration newsBusProducerConfiguration) {
        return kafkaProducerFactory.createKafkaProducer(newsBusProducerConfiguration);
    }

    @Bean("flushExecutor")
    ThreadPoolTaskExecutor flushExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.initialize();
        return executor;
    }

    @Bean
    OutboundBusMessageHandler outboundBusMessageHandler(@Qualifier("newsBusProducer") Producer<String, String> newsBusProducer,
                                                        @Qualifier("flushExecutor") ThreadPoolTaskExecutor flushExecutor,
                                                        @Value("${topics.newsBus}") String newsBusTopic,
                                                        FlushConfiguration flushConfiguration,
                                                        ApplicationStop applicationStop) {
        ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();
        MessageProducer messageProducer = new MessageProducer(newsBusProducer, flushExecutor, flushConfiguration, applicationStop);
        return new OutboundBusMessageHandler(objectMapper, messageProducer, newsBusTopic);
    }

    @Bean
    NewsBusCommunication newsBusCommunication(OutboundBusMessageHandler outboundBusMessageHandler) {
        return new NewsBusCommunication(outboundBusMessageHandler);
    }

}

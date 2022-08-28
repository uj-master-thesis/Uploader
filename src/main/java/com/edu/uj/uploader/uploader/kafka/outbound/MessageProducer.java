package com.edu.uj.uploader.uploader.kafka.outbound;

import com.edu.uj.uploader.uploader.ExitStatus;
import com.edu.uj.uploader.uploader.kafka.FlushConfiguration;
import com.edu.uj.uploader.uploader.utils.ApplicationStop;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class MessageProducer {
    private final Producer<String, String> producer;
    private final ThreadPoolTaskExecutor flushExecutor;
    private final int flushTimeoutSeconds;
    private final int flushMaxTries;
    private final ApplicationStop applicationStop;

    public MessageProducer(Producer<String, String> producer, ThreadPoolTaskExecutor flushExecutor, FlushConfiguration flushConfiguration, ApplicationStop applicationStop) {
        this.producer = producer;
        this.flushExecutor = flushExecutor;
        this.flushTimeoutSeconds = flushConfiguration.getTimeoutSeconds();
        this.flushMaxTries = flushConfiguration.getMaxTries();
        this.applicationStop = applicationStop;
    }

    public Future<RecordMetadata> send(ProducerRecord<String, String> record) {
        log.debug("Sending message: {} with key {} from partition {} headers {}", record.value(), record.key(), record.partition(), record.headers().toString());
        return producer.send(record, (recordMetadata, exception) -> handleSendResult(record, recordMetadata, exception));
    }

    private void handleSendResult(ProducerRecord<String, String> message, RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            String topic = null;
            Long offset = null;
            Integer partition = null;
            if (metadata != null) {
                topic = metadata.topic();
                offset = metadata.offset();
                partition = metadata.partition();
            }
            String messageContent = null;
            if (message != null) {
                messageContent = message.toString();
            }
            log.error("Error while sending kafka message to topic: {}, partition {}, offset {}. Message data: {}",
                    topic, partition, offset, messageContent, exception);
            applicationStop.stop(ExitStatus.KAFKA_PRODUCER_FAILURE);
        } else {
            log.debug("Message send successfully. Timestamp: {}", message.timestamp());
        }
    }

    public void close() {
        producer.close();
    }

    public void flush() {
        boolean flushed = false;
        for (int attempt = 0; attempt < flushMaxTries; ++attempt) {
            log.trace("Flushing producer attempt: {}", attempt);
            Future<?> future = flushExecutor.submit(producer::flush);
            try {
                future.get(flushTimeoutSeconds, TimeUnit.SECONDS);
                log.trace("Trying flush producer on {} attempt", attempt);
                flushed = true;
                break;
            } catch (TimeoutException e) {
                log.warn("Timeout while flushing producer attempt: {}", attempt, e);
                future.cancel(true);
            } catch (InterruptedException e) {
                log.error("Interrupted exception while flushing producer", e);
                Thread.currentThread().interrupt();
                break;
            } catch (ExecutionException e) {
                String msg = "Execution exception while flushing producer";
                log.error(msg);
                //TODO: create own exception
                throw new RuntimeException(msg, e);
            }
        }
        if (!flushed && !Thread.currentThread().isInterrupted()) {
            log.error("Producer not flushed after {} attempts", flushMaxTries);
            throw new RuntimeException("Producer not flushed after" + flushMaxTries + "attempts");
        }
    }
}

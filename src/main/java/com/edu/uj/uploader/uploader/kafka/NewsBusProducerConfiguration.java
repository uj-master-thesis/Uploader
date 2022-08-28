package com.edu.uj.uploader.uploader.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("news-bus.producer")
public class NewsBusProducerConfiguration implements ProducerConfiguration {
    String server;
    int batchSize;
    int serverPort;
    int bufferMemory;

    @Override
    public String getServer() {
        return server;
    }

    @Override
    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public int getBatchSize() {
        return batchSize;
    }

    @Override
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public int getServerPort() {
        return serverPort;
    }

    @Override
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public int getBufferMemory() {
        return bufferMemory;
    }

    @Override
    public void setBufferMemory(int bufferMemory) {
        this.bufferMemory = bufferMemory;
    }
}

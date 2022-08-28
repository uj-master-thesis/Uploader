package com.edu.uj.uploader.uploader.kafka;

public interface ProducerConfiguration {
    String getServer();

    void setServer(String server);

    int getBatchSize();

    void setBatchSize(int batchSize);

    int getServerPort();

    void setServerPort(int serverPort);

    int getBufferMemory();

    void setBufferMemory(int bufferMemory);
}

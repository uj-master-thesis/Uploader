package com.edu.uj.uploader.uploader.kafka.outbound;

public interface OutboundMessageHandler {

    void handle(CommonOutboundMessage msg);

    void close();

    void flush();
}

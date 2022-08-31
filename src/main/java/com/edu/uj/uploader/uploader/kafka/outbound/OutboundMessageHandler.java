package com.edu.uj.uploader.uploader.kafka.outbound;

public interface OutboundMessageHandler {

    void handle(CommonOutboundMessage msg, boolean sendSync);

    void close();

    void flush();
}

package com.edu.uj.uploader.uploader.kafka.outbound;

import java.util.Map;

public class NewsOutboundMessageBuilder {

    private static final int VERSION = 1;

    private final NewsBusOutboundMessage message;

    public NewsOutboundMessageBuilder(NewsBusOutboundMessage message) {
        this.message = message;
    }

    public static NewsOutboundMessageBuilder builder() {
        NewsBusOutboundMessage message = new NewsBusOutboundMessage();
        return new NewsOutboundMessageBuilder(message);
    }

    public NewsOutboundMessageBuilder withAction(String action) {
        message.setAction(action);
        return this;
    }

    public NewsOutboundMessageBuilder withVersion(int version) {
        message.setVersion(version);
        return this;
    }

    public NewsOutboundMessageBuilder withData(Map<String, Object> data) {
        message.setData(data);
        return this;
    }

    public NewsBusOutboundMessage build() {
        return message;
    }
}

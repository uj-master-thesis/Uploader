package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;

public class CommandContext {

    OutboundMessageHandler outboundMessageHandler;

    public CommandContext(OutboundMessageHandler outboundMessageHandler) {
        this.outboundMessageHandler = outboundMessageHandler;
    }

    public OutboundMessageHandler getOutboundMessageHandler() {
        return outboundMessageHandler;
    }
}

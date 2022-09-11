package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;

public class CommandContext {

    OutboundMessageHandler outboundMessageHandler;
    Datastore datastore;

    public CommandContext(OutboundMessageHandler outboundMessageHandler, Datastore datastore) {
        this.outboundMessageHandler = outboundMessageHandler;
        this.datastore = datastore;
    }

    public OutboundMessageHandler getOutboundMessageHandler() {
        return outboundMessageHandler;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}

package com.edu.uj.uploader.uploader.kafka;

import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.domain.observers.AddThreadEventObserver;
import com.edu.uj.uploader.uploader.domain.observers.EventObserver;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;

import java.util.LinkedList;
import java.util.List;

public class NewsBusCommunication {

    private final OutboundMessageHandler outboundMessageHandler;

    public NewsBusCommunication(OutboundMessageHandler outboundMessageHandler) {
        this.outboundMessageHandler = outboundMessageHandler;
    }

    public List<EventObserver<? extends Event>> eventsObservers() {
        List<EventObserver<? extends Event>> eventObservers = new LinkedList<>();
        eventObservers.add(new AddThreadEventObserver(outboundMessageHandler));
        return eventObservers;
    }

}

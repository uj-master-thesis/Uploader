package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.domain.event.EventType;

public interface EventObserver<EVENT extends Event> {

    void processEvent(EVENT event);

    EventType eventType();

    String observerName();
}

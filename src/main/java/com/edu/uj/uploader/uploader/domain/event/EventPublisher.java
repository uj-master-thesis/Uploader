package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.observers.EventObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.edu.uj.uploader.uploader.domain.event.EventType.EMPTY_EVENT;

@Slf4j
public class EventPublisher implements Publisher {

    private final Map<EventType, List<EventObserver<? extends Event>>> observersByEventType = new HashMap<>();

    @Override
    public void register(EventObserver<? extends Event> eventObserver) {
        if (null == eventObserver.eventType() || EMPTY_EVENT.equals(eventObserver.eventType())) {
            throw new IllegalArgumentException("Registering EmptyEventObserver is not allowed.");
        }
        log.info("Registering new eventObserver with name: {} and with eventType: {} into publisher", eventObserver.observerName(), eventObserver.eventType());
        List<EventObserver<? extends Event>> eventObservers = observersByEventType.computeIfAbsent(eventObserver.eventType(), eventType -> new LinkedList<>());
        eventObservers.add(eventObserver);
        observersByEventType.put(eventObserver.eventType(), eventObservers);
    }

    @Override
    public void publish(Event event) {
        EventType eventType = event.getEventType();
        log.trace("Publishing event {} with command result: {}. {}", eventType, event.getCommandResult(), event);
        List<EventObserver<? extends Event>> eventObservers = observersByEventType.getOrDefault(eventType, Collections.emptyList());
        for (EventObserver eventObserver : eventObservers) {
            eventObserver.processEvent(event);
        }
    }
}

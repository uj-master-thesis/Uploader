package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.domain.event.Publisher;

import java.util.Collection;

public class ObserversBinder {
    private final Publisher publisher;
    private final Collection<EventObserver<? extends Event>> observers;

    public ObserversBinder(Publisher publisher, Collection<EventObserver<? extends Event>> observers) {
        this.publisher = publisher;
        this.observers = observers;
    }

    public void bind() {
        for (EventObserver<? extends Event> observer : observers) {
            publisher.register(observer);
        }
    }
}

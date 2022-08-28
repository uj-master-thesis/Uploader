package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.observers.EventObserver;

public interface Publisher {

    void register(EventObserver<? extends Event> eventObserver);

    void publish(Event event);
}

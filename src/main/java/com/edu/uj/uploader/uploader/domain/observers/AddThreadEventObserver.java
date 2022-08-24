package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.event.AddThreadEvent;
import com.edu.uj.uploader.uploader.domain.event.EventType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddThreadEventObserver implements EventObserver<AddThreadEvent>{
    @Override
    public void processEvent(AddThreadEvent event) {
      log.info(event.getEventType().getName());
    }

    @Override
    public EventType eventType() {
        return EventType.ADD_THREAD_EVENT;
    }

    @Override
    public String observerName() {
        return AddThreadEventObserver.class.getSimpleName();
    }
}

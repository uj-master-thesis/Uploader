package com.edu.uj.uploader.uploader.domain.event;

public enum EventType {

    ADD_THREAD_EVENT("addThreadEvent"),
    EMPTY_EVENT("emptyEvent");
    private final String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

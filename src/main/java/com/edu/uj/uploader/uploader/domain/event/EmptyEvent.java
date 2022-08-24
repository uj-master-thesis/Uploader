package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;

public class EmptyEvent implements Event {

    private static final EmptyEvent EMPTY_EVENT = new EmptyEvent();

    public static EmptyEvent event() {
        return EMPTY_EVENT;
    }

    @Override
    public ResultWithReason getCommandResult() {
        return null;
    }

    @Override
    public EventType getEventType() {
        return null;
    }
}

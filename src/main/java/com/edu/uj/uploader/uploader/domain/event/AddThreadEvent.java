package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;

public class AddThreadEvent implements Event {

    private final ThreadRequest threadRequest;

    public AddThreadEvent(ThreadRequest threadRequest) {
        this.threadRequest = threadRequest;
    }

    @Override
    public ResultWithReason getCommandResult() {
        return null;
    }

    @Override
    public EventType getEventType() {
        return EventType.ADD_THREAD_EVENT;
    }
}

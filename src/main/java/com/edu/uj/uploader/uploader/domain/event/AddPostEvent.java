package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;
import com.edu.uj.uploader.uploader.rest.model.PostRequest;

public class AddPostEvent implements Event {
    private final PostRequest postRequest;

    public AddPostEvent(PostRequest postRequest) {
        this.postRequest = postRequest;
    }

    @Override
    public ResultWithReason getCommandResult() {
        return null;
    }

    @Override
    public EventType getEventType() {
        return EventType.ADD_POST_EVENT;
    }
}

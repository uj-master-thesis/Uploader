package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;
import com.edu.uj.uploader.uploader.rest.model.CommentRequest;

public class AddCommentEvent implements Event {
    private final CommentRequest commentRequest;

    public AddCommentEvent(CommentRequest commentRequest) {
        this.commentRequest = commentRequest;
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

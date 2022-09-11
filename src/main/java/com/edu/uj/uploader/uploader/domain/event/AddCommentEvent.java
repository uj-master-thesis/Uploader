package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;
import com.edu.uj.uploader.uploader.rest.model.CommentRequest;

public class AddCommentEvent implements Event {
    private final CommentRequest commentRequest;
    private final ResultWithReason result;

    public AddCommentEvent(CommentRequest commentRequest, ResultWithReason result) {
        this.commentRequest = commentRequest;
        this.result = result;
    }

    @Override
    public ResultWithReason getCommandResult() {
        return result;
    }

    @Override
    public EventType getEventType() {
        return EventType.ADD_COMMENT_EVENT;
    }

    public CommentRequest getCommentRequest() {
        return commentRequest;
    }
}

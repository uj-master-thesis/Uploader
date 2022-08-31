package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;
import com.edu.uj.uploader.uploader.rest.model.VoteRequest;

public class AddVoteEvent implements Event {
    private final VoteRequest voteRequest;

    public AddVoteEvent(VoteRequest voteRequest) {
        this.voteRequest = voteRequest;
    }

    @Override
    public ResultWithReason getCommandResult() {
        return null;
    }

    @Override
    public EventType getEventType() {
        return EventType.ADD_VOTE_EVENT;
    }
}
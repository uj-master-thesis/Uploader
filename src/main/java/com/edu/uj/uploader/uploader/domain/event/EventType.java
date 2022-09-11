package com.edu.uj.uploader.uploader.domain.event;

public enum EventType {

    ADD_THREAD_EVENT("addThreadEvent"),
    ADD_POST_EVENT("addPostEvent"),
    ADD_COMMENT_EVENT("addCommentEvent"),
    ADD_VOTE_EVENT("addVoteEvent"),
    ADD_SUBSCRIPTION_EVENT("addSubscriptionEvent"),
    EMPTY_EVENT("emptyEvent");
    private final String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

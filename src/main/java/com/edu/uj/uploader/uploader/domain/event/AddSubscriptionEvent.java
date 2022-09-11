package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;
import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;

public class AddSubscriptionEvent implements Event {
    private final SubscribedUser subscribedUser;
    private final ResultWithReason resultWithReason;

    public AddSubscriptionEvent(SubscribedUser subscribedUser, ResultWithReason resultWithReason) {
        this.subscribedUser = subscribedUser;
        this.resultWithReason = resultWithReason;
    }

    @Override
    public ResultWithReason getCommandResult() {
        return resultWithReason;
    }

    @Override
    public EventType getEventType() {
        return EventType.ADD_SUBSCRIPTION_EVENT;
    }

    public SubscribedUser getSubscribedUser() {
        return subscribedUser;
    }
}

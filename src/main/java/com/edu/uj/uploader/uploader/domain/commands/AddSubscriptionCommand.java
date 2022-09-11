package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddSubscriptionEvent;
import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddSubscriptionCommand extends VoidCommand<AddSubscriptionEvent> {

    private final SubscribedUser subscribedUser;
    private final ResultWithReason result;

    public AddSubscriptionCommand(SubscribedUser subscribedUser) {
        this.subscribedUser = subscribedUser;
        result = ResultWithReason.ofSuccess();
    }

    @Override
    public ResultWithReason commandResult() {
        return result;
    }

    @Override
    public CommandType commandType() {
        return null;
    }

    @Override
    public AddSubscriptionEvent event() {
        return new AddSubscriptionEvent(subscribedUser, result);
    }

    @Override
    protected void process() {
        log.info("Add subscription: {} for user: {}", subscribedUser, subscribedUser.getUsername());
    }
}

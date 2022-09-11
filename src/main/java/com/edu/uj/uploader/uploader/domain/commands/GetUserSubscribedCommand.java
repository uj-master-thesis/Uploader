package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetUserSubscribedCommand implements QueryCommand<SubscribedUser> {

    private ResultWithReason result;
    private final String username;
    private final Datastore datastore;

    public GetUserSubscribedCommand(String username, CommandContext commandContext) {
        this.username = username;
        this.datastore = commandContext.getDatastore();
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
    public SubscribedUser execute() {
        log.info("Retrieving user with username:{}", username);
        SubscribedUser subscribedUser = datastore.findByUsername(username);
        log.info("Retrieved user: {}", subscribedUser);
        result = null != subscribedUser ? ResultWithReason.ofSuccess() : ResultWithReason.ofFailure();
        return subscribedUser;
    }
}

package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.commands.Result;
import com.edu.uj.uploader.uploader.domain.event.AddSubscriptionEvent;
import com.edu.uj.uploader.uploader.domain.event.EventType;
import com.edu.uj.uploader.uploader.persistence.datastore.DatabaseTypesConverter;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;
import com.edu.uj.uploader.uploader.persistence.model.DbSubscribedUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddSubscriptionToDbObserver implements EventObserver<AddSubscriptionEvent> {
    private final Datastore datastore;
    private final DatabaseTypesConverter converter;

    public AddSubscriptionToDbObserver(Datastore datastore) {
        this.datastore = datastore;
        this.converter = datastore.getDatabaseTypesConverter();
    }

    @Override
    public void processEvent(AddSubscriptionEvent event) {
        if (event.getCommandResult().getResult().equals(Result.SUCCESS)) {
            DbSubscribedUser dbSubscribedUser = converter.toDbSubscribedUser(event.getSubscribedUser());
            log.info("Adding subscribedUser to database: {}", dbSubscribedUser);
            this.datastore.setSubscribedUser(dbSubscribedUser);
        } else {
            log.info("Failed to add subscribedUser to database");
        }
    }

    @Override
    public EventType eventType() {
        return EventType.ADD_SUBSCRIPTION_EVENT;
    }

    @Override
    public String observerName() {
        return AddSubscriptionToDbObserver.class.getSimpleName();
    }
}

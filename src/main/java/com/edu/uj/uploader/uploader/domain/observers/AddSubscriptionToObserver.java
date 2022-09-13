package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.commands.Result;
import com.edu.uj.uploader.uploader.domain.event.AddSubscriptionEvent;
import com.edu.uj.uploader.uploader.domain.event.EventType;
import com.edu.uj.uploader.uploader.persistence.datastore.DatabaseTypesConverter;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;
import com.edu.uj.uploader.uploader.persistence.model.DbSubscribedUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddSubscriptionToObserver implements EventObserver<AddSubscriptionEvent> {
    private final Datastore datastoreSQL;
    private final DatabaseTypesConverter converter;

    public AddSubscriptionToObserver(Datastore datastore, DatabaseTypesConverter databaseTypesConverter) {
        this.datastoreSQL = datastore;
        this.converter = databaseTypesConverter;
    }

    @Override
    public void processEvent(AddSubscriptionEvent event) {
        if (event.getCommandResult().getResult().equals(Result.SUCCESS)) {
            DbSubscribedUser dbSubscribedUser = converter.toDbSubscribedUser(event.getSubscribedUser());
            log.info("Adding subscribedUser to database: {}", dbSubscribedUser);
            this.datastoreSQL.setSubscribedUser(dbSubscribedUser);
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
        return AddSubscriptionToObserver.class.getSimpleName();
    }
}

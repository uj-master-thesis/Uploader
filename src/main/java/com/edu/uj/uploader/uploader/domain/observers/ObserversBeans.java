package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.email.NotificationService;
import com.edu.uj.uploader.uploader.persistence.datastore.DatabaseTypesConverter;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;

import java.util.LinkedList;
import java.util.List;

public class ObserversBeans {
    private final Datastore datastore;
    private final NotificationService notificationService;

    public ObserversBeans(Datastore datastore, NotificationService notificationService) {
        this.datastore = datastore;
        this.notificationService = notificationService;
    }

    public List<EventObserver<? extends Event>> dbObservers() {
        List<EventObserver<? extends Event>> eventObservers = new LinkedList<>();
        eventObservers.add(new AddSubscriptionToObserver(datastore, new DatabaseTypesConverter()));
        eventObservers.add(new SendEmailForNewCommentObserver(datastore, notificationService));
        return eventObservers;
    }

}

package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.email.EmailService;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;

import java.util.LinkedList;
import java.util.List;

public class ObserversBeans {
    private final Datastore datastore;
    private final EmailService emailService;

    public ObserversBeans(Datastore datastore, EmailService emailService) {
        this.datastore = datastore;
        this.emailService = emailService;
    }

    public List<EventObserver<? extends Event>> dbObservers() {
        List<EventObserver<? extends Event>> eventObservers = new LinkedList<>();
        eventObservers.add(new AddSubscriptionToDbObserver(datastore));
        eventObservers.add(new SendEmailForNewCommentObserver(datastore, emailService));
        return eventObservers;
    }

}

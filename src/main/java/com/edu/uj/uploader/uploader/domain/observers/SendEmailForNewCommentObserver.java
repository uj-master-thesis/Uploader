package com.edu.uj.uploader.uploader.domain.observers;

import com.edu.uj.uploader.uploader.domain.commands.Result;
import com.edu.uj.uploader.uploader.domain.event.AddCommentEvent;
import com.edu.uj.uploader.uploader.domain.event.EventType;
import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.email.EmailService;
import com.edu.uj.uploader.uploader.persistence.datastore.Datastore;
import com.edu.uj.uploader.uploader.rest.model.CommentRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendEmailForNewCommentObserver implements EventObserver<AddCommentEvent> {
    private final Datastore datastore;
    private final EmailService emailService;

    public SendEmailForNewCommentObserver(Datastore datastore, EmailService emailService) {
        this.datastore = datastore;
        this.emailService = emailService;
    }

    @Override
    public void processEvent(AddCommentEvent event) {
        if (event.getCommandResult().getResult().equals(Result.SUCCESS)) {
            CommentRequest commentRequest = event.getCommentRequest();
            String username = commentRequest.getUsername();
            SubscribedUser subscribedUser = datastore.findByUsername(username);
            if (subscribedUser.isSubscribed()) {
                log.info("User: {} will be notified about new event", username);
                this.emailService.sendSimpleMessage(username, "Added comment", "Added comment from your account");
                log.info("User: {} notified about new event", username);
            } else {
                log.info("User: {} is not subscribed and will not be notified", username);
            }
        } else {
            log.info("Failed to send email to user related with comment");
        }
    }

    @Override
    public EventType eventType() {
        return EventType.ADD_COMMENT_EVENT;
    }

    @Override
    public String observerName() {
        return SendEmailForNewCommentObserver.class.getSimpleName();
    }
}

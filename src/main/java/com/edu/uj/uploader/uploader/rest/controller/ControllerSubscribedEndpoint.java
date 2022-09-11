package com.edu.uj.uploader.uploader.rest.controller;

import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.rest.model.SubscribeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class ControllerSubscribedEndpoint {
    private final InboundRestPort inboundRestPort;
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
    private static final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;

    public ControllerSubscribedEndpoint(InboundRestPort inboundRestPort) {
        this.inboundRestPort = inboundRestPort;
    }

    @GetMapping(path = "subscribed/{username}",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> subscribed(@PathVariable String username) {
        log.info("Starting check if user: {} subscribed", username);
        SubscribedUser subscribedUser = inboundRestPort.getSubscribedUser(username);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).body(subscribedUser);
    }

    @PostMapping(path = "subscribe",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> subscribe(@Valid @RequestBody SubscribeRequest subscribeRequest) {
        log.info("Starting user subscribe request: {}", subscribeRequest);
        inboundRestPort.addSubscriptionUser(subscribeRequest);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }
}

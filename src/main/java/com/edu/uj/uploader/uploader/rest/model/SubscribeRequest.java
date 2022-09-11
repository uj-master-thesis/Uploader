package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribeRequest {
    private final String username;
    private final Boolean subscribed;

    @JsonCreator
    public SubscribeRequest(
            @JsonProperty("subscribed") Boolean subscribed,
            @JsonProperty("username") String username) {
        this.subscribed = subscribed;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" +
                "username='" + username + '\'' +
                ", subscribed=" + subscribed +
                '}';
    }
}

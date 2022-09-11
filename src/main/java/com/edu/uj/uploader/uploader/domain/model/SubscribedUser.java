package com.edu.uj.uploader.uploader.domain.model;

public class SubscribedUser {
    private final String username;
    private final boolean subscribed;

    public SubscribedUser(String username, boolean subscribed) {
        this.username = username;
        this.subscribed = subscribed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isSubscribed() {
        return subscribed;
    }
}

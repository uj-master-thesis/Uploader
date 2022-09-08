package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadRequest {

    private final String name;
    private final String description;
    private final String username;

    @JsonCreator
    public ThreadRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("username") String username) {
        this.name = name;
        this.description = description;
        this.username = username;
    }

    @Override
    public String toString() {
        return "ThreadRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }
}

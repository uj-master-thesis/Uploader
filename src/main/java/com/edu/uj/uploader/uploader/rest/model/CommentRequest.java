package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentRequest {

    private final String text;
    private final String postName;
    private final String username;
    private final String duration;

    @JsonCreator
    public CommentRequest(
            @JsonProperty("text") String text,
            @JsonProperty("postName") String postName,
            @JsonProperty("username") String username,
            @JsonProperty("duration") String duration) {
        this.text = text;
        this.postName = postName;
        this.username = username;
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public String getPostName() {
        return postName;
    }

    public String getUsername() {
        return username;
    }

    public String getDuration() {
        return duration;
    }
}

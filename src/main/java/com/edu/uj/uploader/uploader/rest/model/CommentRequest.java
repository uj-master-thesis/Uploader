package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentRequest {

    private final String text;
    private final String postId;
    private final String username;
    private final String duration;

    @JsonCreator
    public CommentRequest(
            @JsonProperty("text") String text,
            @JsonProperty("postId") String postId,
            @JsonProperty("username") String username,
            @JsonProperty("duration") String duration) {
        this.text = text;
        this.postId = postId;
        this.username = username;
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public String getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }

    public String getDuration() {
        return duration;
    }
}

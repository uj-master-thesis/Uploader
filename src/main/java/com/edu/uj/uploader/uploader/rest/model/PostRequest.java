package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {
    private final String postName;
    private final String threadName;
    private final String url;
    private final String description;

    @JsonCreator
    public PostRequest(
            @JsonProperty("postName") String postName,
            @JsonProperty("threadName") String threadName,
            @JsonProperty("url") String url,
            @JsonProperty("description") String description) {
        this.postName = postName;
        this.threadName = threadName;
        this.url = url;
        this.description = description;
    }

    public String getPostName() {
        return postName;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PostRequest{" +
                "postName='" + postName + '\'' +
                ", threadName='" + threadName + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
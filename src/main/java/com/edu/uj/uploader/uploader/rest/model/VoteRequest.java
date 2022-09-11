package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteRequest {
    private final String voteType;
    private final String username;
    private final String postName;

    @JsonCreator
    public VoteRequest(
            @JsonProperty("voteType") String voteType,
            @JsonProperty("username") String username,
            @JsonProperty("postName") String postName) {
        this.voteType = voteType;
        this.username = username;
        this.postName = postName;
    }

    public String getVoteType() {
        return voteType;
    }

    public String getUsername() {
        return username;
    }

    public String getPostName() {
        return postName;
    }

    @Override
    public String toString() {
        return "VoteRequest{" +
                "voteType='" + voteType + '\'' +
                ", username='" + username + '\'' +
                ", postName='" + postName + '\'' +
                '}';
    }
}

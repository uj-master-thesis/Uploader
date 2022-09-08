package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteRequest {
    private final String voteType;
    private final String username;
    private final int postNumber;

    @JsonCreator
    public VoteRequest(
            @JsonProperty("voteType") String voteType,
            @JsonProperty("username") String username,
            @JsonProperty("postNumber") int postNumber) {
        this.voteType = voteType;
        this.username = username;
        this.postNumber = postNumber;
    }

    public String getVoteType() {
        return voteType;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "VoteRequest{" +
                "voteType='" + voteType + '\'' +
                ", postNumber=" + postNumber +
                ", username=" + username +
                '}';
    }
}

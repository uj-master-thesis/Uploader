package com.edu.uj.uploader.uploader.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteRequest {
    private final String voteType;
    private final int postNumber;

    @JsonCreator
    public VoteRequest(
            @JsonProperty("voteType") String voteType,
            @JsonProperty("postNumber") int postNumber) {
        this.voteType = voteType;
        this.postNumber = postNumber;
    }

    public String getVoteType() {
        return voteType;
    }

    public int getPostNumber() {
        return postNumber;
    }

    @Override
    public String toString() {
        return "VoteRequest{" +
                "voteType='" + voteType + '\'' +
                ", postNumber=" + postNumber +
                '}';
    }
}

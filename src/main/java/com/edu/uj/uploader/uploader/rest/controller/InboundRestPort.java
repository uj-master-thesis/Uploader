package com.edu.uj.uploader.uploader.rest.controller;

import com.edu.uj.uploader.uploader.rest.model.CommentRequest;
import com.edu.uj.uploader.uploader.rest.model.PostRequest;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;
import com.edu.uj.uploader.uploader.rest.model.VoteRequest;

public interface InboundRestPort {

    void addThread(ThreadRequest threadRequest);

    void addPost(PostRequest postRequest);

    void addComment(CommentRequest commentRequest);

    void addVote(VoteRequest voteRequest);
}

package com.edu.uj.uploader.uploader.rest.controller;

import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.rest.model.*;

public interface InboundRestPort {

    void addThread(ThreadRequest threadRequest);

    void addPost(PostRequest postRequest);

    void addComment(CommentRequest commentRequest);

    void addVote(VoteRequest voteRequest);

    SubscribedUser getSubscribedUser(String username);

    void addSubscriptionUser(SubscribeRequest request);
}

package com.edu.uj.uploader.uploader.rest.controller;

import com.edu.uj.uploader.uploader.domain.commands.*;
import com.edu.uj.uploader.uploader.domain.model.SubscribedUser;
import com.edu.uj.uploader.uploader.domain.processing.Processor;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.rest.model.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommandFacade implements InboundRestPort {

    private final Processor processor;
    private final CommandContext commandContext;
    private final NewsDataConverter newsDataConverter;

    public CommandFacade(@Qualifier("concurrentProcessor") Processor processor, CommandContext commandContext, NewsDataConverter newsDataConverter) {
        this.processor = processor;
        this.commandContext = commandContext;
        this.newsDataConverter = newsDataConverter;
    }

    @Override
    public void addThread(ThreadRequest threadRequest) {
        AddThreadCommand addThreadCommand = new AddThreadCommand(threadRequest, commandContext, newsDataConverter);
        processor.process(addThreadCommand);
    }

    @Override
    public void addPost(PostRequest postRequest) {
        AddPostCommand addPostCommand = new AddPostCommand(postRequest, commandContext, newsDataConverter);
        processor.process(addPostCommand);
    }

    @Override
    public void addComment(CommentRequest commentRequest) {
        AddCommentCommand addCommentCommand = new AddCommentCommand(commentRequest, commandContext, newsDataConverter);
        processor.process(addCommentCommand);
    }

    @Override
    public void addVote(VoteRequest voteRequest) {
        AddVoteCommand addVoteCommand = new AddVoteCommand(voteRequest, commandContext, newsDataConverter);
        processor.process(addVoteCommand);
    }

    @Override
    public SubscribedUser getSubscribedUser(String username) {
        GetUserSubscribedCommand getUserSubscribedCommand = new GetUserSubscribedCommand(username, commandContext);
        return processor.process(getUserSubscribedCommand);
    }

    @Override
    public void addSubscriptionUser(SubscribeRequest request) {
        SubscribedUser subscribedUser = new SubscribedUser(request.getUsername(), request.getSubscribed());
        AddSubscriptionCommand addSubscriptionCommand = new AddSubscriptionCommand(subscribedUser);
        processor.process(addSubscriptionCommand);
    }
}

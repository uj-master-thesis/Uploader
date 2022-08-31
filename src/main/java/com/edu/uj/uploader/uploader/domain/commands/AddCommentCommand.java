package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddCommentEvent;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsBusOutboundMessage;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsOutboundMessageBuilder;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.rest.model.CommentRequest;

import java.util.Map;

public class AddCommentCommand extends VoidCommand<AddCommentEvent> {

    private final CommentRequest commentRequest;
    private final OutboundMessageHandler outboundMessageHandler;
    private final NewsDataConverter newsDataConverter;

    public AddCommentCommand(CommentRequest commentRequest, CommandContext commandContext, NewsDataConverter newsDataConverter) {
        this.commentRequest = commentRequest;
        this.outboundMessageHandler = commandContext.getOutboundMessageHandler();
        this.newsDataConverter = newsDataConverter;
    }

    @Override
    public ResultWithReason commandResult() {
        return null;
    }

    @Override
    public CommandType commandType() {
        return null;
    }

    @Override
    public void process() {
        Map<String, Object> data = newsDataConverter.convert(commentRequest);
        NewsBusOutboundMessage newsBusOutboundMessage = NewsOutboundMessageBuilder.builder()
                .withVersion(1)
                .withAction("addComment")
                .withData(data)
                .build();
        outboundMessageHandler.handle(newsBusOutboundMessage, true);
    }

    @Override
    public AddCommentEvent event() {
        return new AddCommentEvent(commentRequest);
    }
}
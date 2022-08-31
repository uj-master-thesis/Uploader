package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddPostEvent;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsBusOutboundMessage;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsOutboundMessageBuilder;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.rest.model.PostRequest;

import java.util.Map;

public class AddPostCommand extends VoidCommand<AddPostEvent> {

    private final PostRequest postRequest;
    private final OutboundMessageHandler outboundMessageHandler;
    private final NewsDataConverter newsDataConverter;

    public AddPostCommand(PostRequest postRequest, CommandContext commandContext, NewsDataConverter newsDataConverter) {
        this.postRequest = postRequest;
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
        Map<String, Object> data = newsDataConverter.convert(postRequest);
        NewsBusOutboundMessage newsBusOutboundMessage = NewsOutboundMessageBuilder.builder()
                .withVersion(1)
                .withAction("addPost")
                .withData(data)
                .build();
        outboundMessageHandler.handle(newsBusOutboundMessage, true);
    }

    @Override
    public AddPostEvent event() {
        return new AddPostEvent(postRequest);
    }
}

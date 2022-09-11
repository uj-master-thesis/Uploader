package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddCommentEvent;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsBusOutboundMessage;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsOutboundMessageBuilder;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.rest.model.CommentRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class AddCommentCommand extends VoidCommand<AddCommentEvent> {

    private final CommentRequest commentRequest;
    private final OutboundMessageHandler outboundMessageHandler;
    private final NewsDataConverter newsDataConverter;
    private ResultWithReason result = ResultWithReason.ofFailure();

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
        log.info("Comment before converting: {}", commentRequest);
        Map<String, Object> data = newsDataConverter.convert(commentRequest);
        NewsBusOutboundMessage newsBusOutboundMessage = NewsOutboundMessageBuilder.builder()
                .withVersion(1)
                .withAction("addComment")
                .withData(data)
                .build();
        log.info("Comment after converting: {}", newsBusOutboundMessage);
        outboundMessageHandler.handle(newsBusOutboundMessage, true);
        result = ResultWithReason.ofSuccess();
    }

    @Override
    public AddCommentEvent event() {
        return new AddCommentEvent(commentRequest, result);
    }
}
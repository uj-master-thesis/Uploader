package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddThreadEvent;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsBusOutboundMessage;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsOutboundMessageBuilder;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class AddThreadCommand extends VoidCommand<AddThreadEvent> {

    private final ThreadRequest threadRequest;
    private final OutboundMessageHandler outboundMessageHandler;
    private final NewsDataConverter newsDataConverter;

    public AddThreadCommand(ThreadRequest threadRequest, CommandContext commandContext, NewsDataConverter newsDataConverter) {
        this.threadRequest = threadRequest;
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
        Map<String, Object> data = newsDataConverter.convert(threadRequest);
        log.info("Thread before converting: {}", threadRequest);
        NewsBusOutboundMessage newsBusOutboundMessage = NewsOutboundMessageBuilder.builder()
                .withVersion(1)
                .withAction("addThread")
                .withData(data)
                .build();
        log.info("Thread after converting: {}", newsBusOutboundMessage);
        outboundMessageHandler.handle(newsBusOutboundMessage, true);
    }

    @Override
    public AddThreadEvent event() {
        return new AddThreadEvent(threadRequest);
    }
}

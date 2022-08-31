package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddThreadEvent;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsBusOutboundMessage;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsOutboundMessageBuilder;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;

import java.util.Map;

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
        NewsBusOutboundMessage newsBusOutboundMessage = NewsOutboundMessageBuilder.builder()
                .withVersion(1)
                .withAction("addThread")
                .withData(data)
                .build();
        outboundMessageHandler.handle(newsBusOutboundMessage, true);
    }

    @Override
    public AddThreadEvent event() {
        return new AddThreadEvent(threadRequest);
    }
}

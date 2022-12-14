package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddVoteEvent;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsBusOutboundMessage;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsOutboundMessageBuilder;
import com.edu.uj.uploader.uploader.kafka.outbound.OutboundMessageHandler;
import com.edu.uj.uploader.uploader.rest.model.VoteRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class AddVoteCommand extends VoidCommand<AddVoteEvent> {
    private final VoteRequest voteRequest;
    private final OutboundMessageHandler outboundMessageHandler;
    private final NewsDataConverter newsDataConverter;

    public AddVoteCommand(VoteRequest voteRequest, CommandContext commandContext, NewsDataConverter newsDataConverter) {
        this.voteRequest = voteRequest;
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
        log.info("Vote before converting: {}", voteRequest);
        Map<String, Object> data = newsDataConverter.convert(voteRequest);
        NewsBusOutboundMessage newsBusOutboundMessage = NewsOutboundMessageBuilder.builder()
                .withVersion(1)
                .withAction("addVote")
                .withData(data)
                .build();
        log.info("Vote after converting: {}", newsBusOutboundMessage);
        outboundMessageHandler.handle(newsBusOutboundMessage, true);
    }

    @Override
    public AddVoteEvent event() {
        return new AddVoteEvent(voteRequest);
    }
}

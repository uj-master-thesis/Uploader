package com.edu.uj.uploader.uploader.rest.controller;

import com.edu.uj.uploader.uploader.domain.commands.*;
import com.edu.uj.uploader.uploader.domain.processing.Processor;
import com.edu.uj.uploader.uploader.kafka.outbound.NewsDataConverter;
import com.edu.uj.uploader.uploader.rest.model.CommentRequest;
import com.edu.uj.uploader.uploader.rest.model.PostRequest;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;
import com.edu.uj.uploader.uploader.rest.model.VoteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController()
public class ControllerReceiverEndpoint {

    private final Processor processor;
    private final CommandContext commandContext;
    private final NewsDataConverter newsDataConverter;
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
    private static final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;

    public ControllerReceiverEndpoint(@Qualifier("concurrentProcessor") Processor processor, CommandContext commandContext, NewsDataConverter newsDataConverter) {
        this.processor = processor;
        this.commandContext = commandContext;
        this.newsDataConverter = newsDataConverter;
    }

    @PostMapping(path = "uploader/thread",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addThread(@Valid @RequestBody ThreadRequest threadRequest) {
        AddThreadCommand addThreadCommand = new AddThreadCommand(threadRequest, commandContext, newsDataConverter);
        processor.process(addThreadCommand);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

    @PostMapping(path = "uploader/post",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addPost(@Valid @RequestBody PostRequest postRequest) {
        AddPostCommand addPostCommand = new AddPostCommand(postRequest, commandContext, newsDataConverter);
        processor.process(addPostCommand);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

    @PostMapping(path = "uploader/comment",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        AddCommentCommand addCommentCommand = new AddCommentCommand(commentRequest, commandContext, newsDataConverter);
        processor.process(addCommentCommand);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

    @PostMapping(path = "uploader/vote",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addVote(@Valid @RequestBody VoteRequest voteRequest) {
        AddVoteCommand addVoteCommand = new AddVoteCommand(voteRequest, commandContext, newsDataConverter);
        processor.process(addVoteCommand);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

}

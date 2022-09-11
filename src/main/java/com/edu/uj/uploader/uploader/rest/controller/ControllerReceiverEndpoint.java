package com.edu.uj.uploader.uploader.rest.controller;

import com.edu.uj.uploader.uploader.rest.model.CommentRequest;
import com.edu.uj.uploader.uploader.rest.model.PostRequest;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;
import com.edu.uj.uploader.uploader.rest.model.VoteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class ControllerReceiverEndpoint {

    private final InboundRestPort inboundRestPort;
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
    private static final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;

    public ControllerReceiverEndpoint(InboundRestPort inboundRestPort) {
        this.inboundRestPort = inboundRestPort;
    }

    @PostMapping(path = "uploader/thread",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addThread(@Valid @RequestBody ThreadRequest threadRequest) {
        inboundRestPort.addThread(threadRequest);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

    @PostMapping(path = "uploader/post",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addPost(@Valid @RequestBody PostRequest postRequest) {
        inboundRestPort.addPost(postRequest);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

    @PostMapping(path = "uploader/comment",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        inboundRestPort.addComment(commentRequest);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

    @PostMapping(path = "uploader/vote",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addVote(@Valid @RequestBody VoteRequest voteRequest) {
        inboundRestPort.addVote(voteRequest);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }

}

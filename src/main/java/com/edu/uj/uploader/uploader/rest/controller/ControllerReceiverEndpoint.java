package com.edu.uj.uploader.uploader.rest.controller;

import com.edu.uj.uploader.uploader.domain.commands.AddThreadCommand;
import com.edu.uj.uploader.uploader.domain.processing.Processor;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;
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
@RestController
public class ControllerReceiverEndpoint {

    private final Processor processor;
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
    private static final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;

    public ControllerReceiverEndpoint(@Qualifier("concurrentProcessor") Processor processor) {
        this.processor = processor;
    }

    @PostMapping(path = "uploader/thread",
            produces = CONTENT_TYPE,
            consumes = CONTENT_TYPE
    )
    ResponseEntity<?> addThread(@Valid @RequestBody ThreadRequest threadRequest) {
        AddThreadCommand addThreadCommand = new AddThreadCommand(threadRequest);
        processor.process(addThreadCommand);
        return ResponseEntity.status(HttpStatus.OK).contentType(MEDIA_TYPE).build();
    }
}

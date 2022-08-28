package com.edu.uj.uploader.uploader.domain.processing;

import com.edu.uj.uploader.uploader.domain.commands.Command;
import com.edu.uj.uploader.uploader.domain.event.Event;
import com.edu.uj.uploader.uploader.domain.event.Publisher;

public class PublishingProcessor implements Processor {

    private final Publisher publisher;

    public PublishingProcessor(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public <RESULT> RESULT process(Command<RESULT, ?> command) {
        RESULT result = command.execute();
        Event event = command.event();
        publisher.publish(event);
        return result;
    }
}

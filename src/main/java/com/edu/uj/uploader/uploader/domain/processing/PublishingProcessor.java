package com.edu.uj.uploader.uploader.domain.processing;

import com.edu.uj.uploader.uploader.domain.commands.Command;

public class PublishingProcessor implements Processor{

    @Override
    public <RESULT> RESULT process(Command<RESULT, ?> command) {
        return null;
    }
}

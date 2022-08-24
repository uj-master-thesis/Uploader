package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.EmptyEvent;

public interface QueryCommand<RESULT> extends Command<RESULT, EmptyEvent> {

    @Override
    default EmptyEvent event() {
        return EmptyEvent.event();
    }

}

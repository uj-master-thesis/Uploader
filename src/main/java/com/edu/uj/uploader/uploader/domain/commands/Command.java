package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.Event;

public interface Command<RESULT, EVENT extends Event> {

    ResultWithReason commandResult();

    CommandType commandType();

    RESULT execute();

    EVENT event();
}

package com.edu.uj.uploader.uploader.domain.event;

import com.edu.uj.uploader.uploader.domain.commands.ResultWithReason;

public interface Event {

    ResultWithReason getCommandResult();

    EventType getEventType();

}

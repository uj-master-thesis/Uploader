package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.AddThreadEvent;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;

public class AddThreadCommand extends VoidCommand<AddThreadEvent>{

    private final ThreadRequest threadRequest;

    public AddThreadCommand(ThreadRequest threadRequest) {
        this.threadRequest = threadRequest;
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

    }

    @Override
    public AddThreadEvent event() {
        return new AddThreadEvent(threadRequest);
    }
}

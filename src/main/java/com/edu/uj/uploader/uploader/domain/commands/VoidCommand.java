package com.edu.uj.uploader.uploader.domain.commands;

import com.edu.uj.uploader.uploader.domain.event.Event;

public abstract class VoidCommand<EVENT extends Event> implements Command<Void, EVENT> {


    @Override
    public Void execute() {
        process();
        return null;
    }

    protected abstract void process();
}

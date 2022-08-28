package com.edu.uj.uploader.uploader.domain.processing;

import com.edu.uj.uploader.uploader.domain.commands.Command;
import com.edu.uj.uploader.uploader.domain.commands.QueryCommand;
import com.edu.uj.uploader.uploader.utils.ApplicationStop;

import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentProcessor implements Processor {
    private final Processor processor;
    private final ApplicationStop applicationStop;
    private final Lock lock = new ReentrantLock();


    public ConcurrentProcessor(Processor processor, ApplicationStop applicationStop) {
        this.processor = processor;
        this.applicationStop = applicationStop;
    }

    @Override
    public <RESULT> RESULT process(Command<RESULT, ?> command) {
        if (command instanceof QueryCommand) {
            return processor.process(command);
        } else {
            submitCommand(command);
        }
        return null;
    }


    private <RESULT> Future<RESULT> submitCommand(Command<RESULT, ?> command) {
        lock.lock();
        processor.process(command);
        lock.unlock();
        return null;
    }
}

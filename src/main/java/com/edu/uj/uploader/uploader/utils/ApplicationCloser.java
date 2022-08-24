package com.edu.uj.uploader.uploader.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class ApplicationCloser implements ApplicationStop {

    private static final int EXIT_CODE = 11;
    private final ApplicationContext applicationContext;
    private final AtomicBoolean shutdownRequested = new AtomicBoolean(false);

    public ApplicationCloser(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void stop() {
        if (shutdownRequested.compareAndSet(false, true)) {
            log.error("Stopping application...");
            SpringApplication.exit(applicationContext, () -> EXIT_CODE);
            log.error("Running system exit...");
            System.exit(EXIT_CODE);
        } else {
            log.warn("Stopping application already requested");
        }
    }
}

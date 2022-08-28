package com.edu.uj.uploader.uploader.utils;

import com.edu.uj.uploader.uploader.ExitStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class ApplicationCloser implements ApplicationStop {
    private final ApplicationContext applicationContext;
    private final AtomicBoolean shutdownRequested = new AtomicBoolean(false);

    public ApplicationCloser(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void stop(ExitStatus exitStatus) {
        if (shutdownRequested.compareAndSet(false, true)) {
            log.error("Stopping application wit status code: {}", exitStatus);
            int exitCode = SpringApplication.exit(applicationContext, () -> exitStatus != null ? exitStatus.getStatus() : ExitStatus.SUCCESS.getStatus());
            log.error("Running system exit...");
            System.exit(exitCode);
        } else {
            log.warn("Stopping application already requested");
        }
    }
}

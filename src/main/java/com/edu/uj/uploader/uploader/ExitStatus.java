package com.edu.uj.uploader.uploader;

public enum ExitStatus {
    SUCCESS(0),
    KAFKA_PRODUCER_FAILURE(1);

    private final int status;

    ExitStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

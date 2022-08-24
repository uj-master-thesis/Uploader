package com.edu.uj.uploader.uploader.rest.model;

public class ThreadRequest {
    private final String name;
    private final String description;

    public ThreadRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

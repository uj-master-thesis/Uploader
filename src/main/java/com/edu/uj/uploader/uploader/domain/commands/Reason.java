package com.edu.uj.uploader.uploader.domain.commands;

public enum Reason {
    EMPTY(""),
    FAILED("");

    private final String text;

    Reason(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

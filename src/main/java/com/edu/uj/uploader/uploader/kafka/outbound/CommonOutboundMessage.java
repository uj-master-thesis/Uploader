package com.edu.uj.uploader.uploader.kafka.outbound;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public abstract class CommonOutboundMessage {
    protected int version;
    protected Map<String, Object> data;
    protected String messageKey;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @JsonIgnore
    public abstract String getMessageName();

    public String getMessageKey() {
        return messageKey;
    }

    @JsonIgnore
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
}

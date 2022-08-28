package com.edu.uj.uploader.uploader.kafka.outbound;

import java.util.Objects;

public class NewsBusOutboundMessage extends CommonOutboundMessage {

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getMessageName() {
        return action;
    }

    @Override
    public String toString() {
        return "NewsBusOutboundMessage{" +
                "action='" + action + '\'' +
                ", version=" + version +
                ", data=" + data +
                ", messageKey='" + messageKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsBusOutboundMessage that = (NewsBusOutboundMessage) o;
        return version == that.version &&
                Objects.equals(data, that.data) &&
                Objects.equals(messageKey, that.messageKey) &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, data, action, messageKey);
    }
}

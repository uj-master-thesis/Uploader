package com.edu.uj.uploader.uploader.email;

public interface NotificationService {
    void sendMessage(String to, String subject, String text);
}

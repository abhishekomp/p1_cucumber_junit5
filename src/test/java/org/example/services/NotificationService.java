package org.example.services;

public interface NotificationService {
    /**
     * Sends a notification to the user.
     *
     * @param userName the name of the user to notify
     * @param message the message to send
     */
    void sendNotification(String userName, String message);
}

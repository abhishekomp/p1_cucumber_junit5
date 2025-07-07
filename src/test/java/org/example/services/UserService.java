package org.example.services;

public class UserService {
    private final NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        System.out.println("[LOG] UserService bean created with notificationService: " + notificationService.getClass());
        this.notificationService = notificationService;
    }

    /**
     * Registers a new user and sends a welcome notification.
     *
     * @param userName the name of the user to register
     */
    public void registerUser(String userName) {
        // Logic to register the user (e.g., save to database)
        System.out.println("User registered: " + userName);
    }

    public void notifyUser(String userName, String message) {
        // Logic to notify the user
        // Send a welcome notification
        //notificationService.sendNotification(userName, "Welcome to our service, " + userName + "!");
        notificationService.sendNotification(message, userName);
    }
}

package org.example.services;

import java.io.PrintStream;

public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String userName) {
        // Simulate sending an email notification
        // In a real application, this would involve using an email service
        //System.out.format("Sending email to %s: %s%n", userName, message);
        // Here we could add logic to actually send the email
        // For example, using JavaMail API or any other email service provider
        //System.out.println(message + ", " + userName + "!");
        //System.out.println("userName = " + userName);
        System.out.format("%s, %s!%n", message, userName); //Welcome to the admin club, Jane Smith!
    }
}

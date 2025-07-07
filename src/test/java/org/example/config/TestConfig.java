package org.example.config;

import org.example.services.EmailNotification;
import org.example.services.NotificationService;
import org.example.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    public TestConfig() {
        System.out.println("[LOG] TestConfig instance created.");
    }
    @Bean
    public NotificationService notificationService() {
        // Return a Mockito mock!
        //return Mockito.mock(NotificationService.class);
        return new EmailNotification();
    }

    @Bean
    public UserService userService(NotificationService notificationService) {
        return new UserService(notificationService);
    }
}
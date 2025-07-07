package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Objects;

@CucumberContextConfiguration
@ContextConfiguration(classes = org.example.config.TestConfig.class)
public class UserRegistrationStepDefs {
    // This class defines the step definitions for user registration feature in Cucumber tests.
    public UserRegistrationStepDefs() {
        System.out.println("[LOG] UserRegistrationStepDefs constructor called.");
    }
//    @Autowired
//    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    private String userName;
    private String userType;

    @Given("A new user has approached for registration")
    public void a_new_user_has_approached_for_registration() {
        System.out.println("New user has approached for registration.");
    }

    @When("I create a new user with name as {string}")
    public void iCreateANewUserWithNameAs(String name) {
        this.userName = name;
        //System.out.println("User is created in the system with name as " + name);
        //System.out.format("User is created in the system with name as %s.%n", userName);
        userService.registerUser(userName);
    }

    @Then("The new user should be registered successfully with the name {string}")
    public void the_new_user_should_be_registered_successfully_with_the_name(String name) {
        System.out.format("New user with name %s is registered successfully in the system.%n", name);
    }

    @Then("The user should receive registration confirmation")
    public void the_user_should_receive_registration_confirmation() {
        System.out.format("Sending registration confirmation to %s.%n", userName);
        //String message = "Welcome to the club";
        String message = Objects.equals(userType, "admin") ? "Welcome to the admin club" : "Welcome to the user club";
        //notificationService.sendNotification(message, userName);
        userService.notifyUser(userName, message);
    }

    @Given("A new {string} user has approached for registration")
    public void aNewUserHasApproachedForRegistration(String userType) {
        //System.out.format("New user has approached for registration.%n");
        this.userType = userType;
        System.out.format("A user of type %s has approached for registration.%n", userType);
    }

    @When("I create a new {string} user with name as {string}")
    public void iCreateANewUserWithNameAs(String userType, String name) {
        this.userName = name;
        System.out.format("New %s user with name %s is registered successfully in the system.%n", userType, name);
    }
}

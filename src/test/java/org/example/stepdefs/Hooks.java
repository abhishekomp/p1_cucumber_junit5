package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.charset.StandardCharsets;

public class Hooks {

    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();

//    @Before
//    public void beforeScenario() {
//        System.out.printf("[LOG] [BEFORE_HOOK] Executing before scenario setup...%n");
//        //System.out.println("Executing before scenario setup...");
//        // Add setup logic here, e.g., initializing resources
//    }

    // This method is called before each scenario
    // The Scenario object is automatically injected into @Before and @After hooks by Cucumber, allowing you to access scenario metadata, log output, or attach artifacts.
    @Before
    public void beforeScenario(Scenario scenario) {
        startTime.set(System.currentTimeMillis());
        System.out.printf("[LOG] [BEFORE_HOOK] Starting scenario: %s%n", scenario.getName());
        // Example: Log scenario tags
        System.out.printf("[LOG] Tags: %s%n", scenario.getSourceTagNames());
        // Example: Attach a message to the scenario
        scenario.log("Before hook executed.");
    }

    // This method is called after each scenario
    // The Scenario object is automatically injected into @Before and @After hooks by Cucumber, allowing you to access scenario metadata, log output, or attach artifacts.
    @After
    public void afterScenario(Scenario scenario) {
        System.out.printf("[LOG] [AFTER_HOOK] Executing after scenario setup...%n");
        //System.out.println("Executing after scenario teardown...");
        // Add cleanup logic here, e.g., closing resources
        long endTime = System.currentTimeMillis();
        long durationMillis = endTime - startTime.get();
        double durationSeconds = durationMillis / 1000.0;
        String message = String.format("Scenario executed in %.3f seconds.", durationSeconds);
        scenario.log(message);
        scenario.attach(message.getBytes(), "text/plain", "execution-time.txt");
        startTime.remove();
    }

    // This method is called after each scenario, allowing you to log the scenario status and attach artifacts
    // A more advanced example that includes attaching artifacts like screenshots or JSON data
/*    @After
    public void afterScenario(Scenario scenario) {
        System.out.printf("[LOG] [AFTER_HOOK] Finished scenario: %s (Status: %s)%n", scenario.getName(), scenario.getStatus());
        scenario.log("After hook executed. Scenario status: " + scenario.getStatus());

        // Attach a summary log
        String summary = String.format("Scenario '%s' finished with status: %s", scenario.getName(), scenario.getStatus());
        scenario.attach(summary.getBytes(StandardCharsets.UTF_8), "text/plain", "scenario-summary.txt");

        // If scenario failed, attach diagnostic data
        if (scenario.isFailed()) {
            // Example: Attach a dummy screenshot (replace with real screenshot in UI tests)
            byte[] dummyScreenshot = "This would be binary screenshot data".getBytes(StandardCharsets.UTF_8);
            scenario.attach(dummyScreenshot, "image/png", "failure-screenshot.png");

            // Example: Attach last found book as JSON (if available)
            Book lastBook = getLastFoundBook();
            if (lastBook != null) {
                String bookJson = String.format(
                        "{\"title\":\"%s\",\"author\":\"%s\",\"genre\":\"%s\",\"publishDate\":\"%s\"}",
                        lastBook.getTitle(), lastBook.getAuthor(), lastBook.getGenre(), lastBook.getPublishDate()
                );
                scenario.attach(bookJson.getBytes(StandardCharsets.UTF_8), "application/json", "last-book.json");
            }
        }
    }

    // Dummy method to simulate access to the last found book
    private Book getLastFoundBook() {
        // In a real project, inject or access shared state here
        return null;
    }*/

}
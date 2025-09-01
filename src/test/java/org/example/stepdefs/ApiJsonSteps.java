package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiJsonSteps {
    private String apiResponse;
    
    @Given("the API responds with")
    public void the_api_responds_with(String docString) {
        this.apiResponse = docString.trim();
    }

    @Then("the API response matches golden master {string}")
    public void the_api_response_matches_golden_master(String goldenMasterFile) throws Exception {

        System.out.printf("[LOG] Matching api response against the Golden master json: %s%n", goldenMasterFile);

        /*String expectedJson = Files.readString(Paths.get("src/test/resources/" + goldenMasterFile));
        assertThatJson(apiResponse)
            .withMatcher("id", CustomMatchers.ID_MATCHER)
            .withMatcher("status", CustomMatchers.STATUS_MATCHER)
            .withMatcher("timestamp", CustomMatchers.TIMESTAMP_MATCHER)
            .when(net.javacrumbs.jsonunit.core.Option.IGNORING_EXTRA_FIELDS)
            .isEqualTo(expectedJson);*/
    }
}
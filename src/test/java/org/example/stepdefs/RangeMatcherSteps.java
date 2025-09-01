package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
//import net.javacrumbs.jsonunit.core.PredicateMatcher; // ADD THIS IMPORT

import java.nio.file.Files;
import java.nio.file.Paths;

public class RangeMatcherSteps {
    private String apiResponse;

/*    @Given("the API responds with")
    public void the_api_responds_with(String docString) {
        this.apiResponse = docString.trim();
    }*/

/*    @Then("the API response matches golden master {string}")
    public void the_api_response_matches_golden_master(String goldenMasterFile) throws Exception {
        System.out.println("API response matches golden master " + goldenMasterFile);
        String expectedJson = Files.readString(Paths.get("src/test/resources/" + goldenMasterFile));
        assertThatJson(apiResponse)
                .withMatcher("rangeMatcher",
                        PredicateMatcher.predicate((actual, expected) -> RangeMatchers.rangeMatcher(actual, expected))
                )
                .isEqualTo(expectedJson);
    }*/
}
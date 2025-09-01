Feature: Validate that a score is within a specified inclusive range using a custom matcher with arguments

  Scenario: Score is within range (should pass)
    Given the API responds with
      """
      { "score": 15 }
      """
    Then the API response matches golden master "golden-score-range.json"

  Scenario: Score is below range (should fail)
    Given the API responds with
      """
      { "score": 5 }
      """
    Then the API response matches golden master "golden-score-range.json"

  Scenario: Score is above range (should fail)
    Given the API responds with
      """
      { "score": 22 }
      """
    Then the API response matches golden master "golden-score-range.json"
Feature: API JSON contract validation with Cucumber and json-unit

  Scenario: Validate user record with custom matchers
    Given the API responds with
      """
      {
        "id": "U123-2024",
        "status": "ACTIVE",
        "timestamp": "2024-06-01T12:34:56Z"
      }
      """
    Then the API response matches golden master "golden-user.json"

  Scenario: Validate transaction with ignored timestamp
    Given the API responds with
      """
      {
        "transactionId": "TX1000",
        "amount": 250.0,
        "timestamp": "2024-06-03T13:00:00Z"
      }
      """
    Then the API response matches golden master "golden-transaction.json"

#  Scenario: Validate transaction API with timestamp ignored
#    When I perform GET api call against transaction api "/api/transaction/1001"
#    Then the HTTP response matches golden master "golden-transaction.json" (ignoring timestamps)
#
#  Scenario: Validate user status with custom matcher
#    When I perform GET api call against user api "/api/user/42"
#    Then the HTTP response matches golden master "golden-user-status.json" with custom matchers
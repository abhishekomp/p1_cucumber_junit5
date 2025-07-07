Feature: User Registration

#  Scenario: Register a new user
#    Given A new user has approached for registration
#    When I create a new user with name as "John Doe"
#    Then The new user should be registered successfully with the name "John Doe"
#    And The user should receive registration confirmation

  Scenario: Register a new normal user
    Given A new "normal" user has approached for registration
    When I create a new "normal" user with name as "John Doe"
    Then The new user should be registered successfully with the name "John Doe"
    And The user should receive registration confirmation

  Scenario: Register an admin user
    Given A new "admin" user has approached for registration
    When I create a new "admin" user with name as "Jane Smith"
    Then The new user should be registered successfully with the name "Jane Smith"
    And The user should receive registration confirmation
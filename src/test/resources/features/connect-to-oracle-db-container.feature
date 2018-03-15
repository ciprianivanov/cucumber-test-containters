Feature: Connect to Oracle DB container

  Scenario: Create Oracle DB container
    Given I want to connect to a Oracle DB container
    When I run a test DB query
    Then the query outputs the result
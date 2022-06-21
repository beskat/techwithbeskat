@api
Feature: Random User API

  Scenario: Retrieve fake user information via API
    When API GET request is made to endpoint "https://randomuser.me/api"
    Then Verify status code is 200
    And Verify response content type is "application/json"
    When Print out the response body to console
    And Print out the user's first name to console
    And Print out the user's last name to console
    And Print out the user's age to console

@ui
Feature: YouTube Test

  Scenario: Verify YouTube channel name
    When I go to page "https://www.youtube.com/c/techwithbeskat"
    Then I verify YouTube channel name "Tech with Beskat"
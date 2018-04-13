# language: en
Feature: Login in the main page fails

  Scenario: Login is not ok username is wrong
    Given I am not a correct operator
    When I try to login
    Then I should see the error page

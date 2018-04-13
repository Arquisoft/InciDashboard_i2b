# language: en
Feature: Login in the main page

  Scenario: Login is ok
    Given I am a correct operator
    When I login with a user
    Then I should see the dashboard page

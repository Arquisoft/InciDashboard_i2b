# language: en
Feature: Login in the main page fails because of wrong password

  Scenario: Login is not ok password is wrong
    Given I am a correct operator
    When I try to login with wrong password
    Then I should see the error page

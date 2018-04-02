# language: en
Feature: Login in the main page
Scenario: Login is ok
Given I am a correct operator
When I login with a user
When I login with a password
Then I should see the dashboard page

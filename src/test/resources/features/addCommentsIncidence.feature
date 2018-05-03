# language: en
Feature: Adding comments to incidences

  Scenario: Incidences are edited with comments
    Given I am a correct operator
    When I login with a user
    And I have incidences
    When I add a comment and it is saved

# language: en
Feature: Checking incidence

  Scenario: Incidences are shown
    Given I am a correct operator
    When I login with a user
    Then I can see my incidences

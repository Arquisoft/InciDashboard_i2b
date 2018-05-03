# language: en
Feature: Checking incidence are zero

  Scenario: Incidences are not shown
    Given I am a correct operator
    When I login with a user
    And My incidences are zero
    Then There are not incidences

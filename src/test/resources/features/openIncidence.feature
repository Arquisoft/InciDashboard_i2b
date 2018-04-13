Feature: An incidence is opened
 
  Scenario: An operator modifies the first of his assigned incidences
    Given operator with email "operator1@dashboard.com"
    And the first of his assigned incidences
    		
    When he opens the incidence
    Then the incidence is opened
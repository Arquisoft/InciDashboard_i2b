Feature: An incidence is cancelled
 
  Scenario: An operator modifies the first of his assigned incidences
    Given operator with email "operator1@dashboard.com"
    And the first of his assigned incidences
    		
    When he in process the incidence
    Then the incidence is in processed
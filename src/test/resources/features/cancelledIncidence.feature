Feature: An incidence is cancelled
 
  Scenario: An operator modifies the first of his assigned incidences
    Given operator with email "david_son@dashboard.com"
    And the first of his assigned incidences
    		
    When he cancels the incidence
    Then the incidence is cancelled
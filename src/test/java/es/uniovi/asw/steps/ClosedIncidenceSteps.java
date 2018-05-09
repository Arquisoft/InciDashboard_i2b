package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClosedIncidenceSteps {	
	/**
	 * If we wanted to use a lambda expression, we would need to modify the dependencies
	 * And the lambda expression would be 
	 * Given("operator with email (\\s+)", (String email) -> {
            System.out.format("Cukes: %n\n", cukes);
            this.operator = opService.getOperatorByEmail(email); 
        });
*/
	WebDriver driver = new FirefoxDriver();
	
	@When("^he closes the incidence$")
	public void he_closes_the_incidence() throws Throwable {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
	}
	
	@Then("^the incidence is closed$")
	public void closed() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.CLOSED));
		driver.quit();
	}

}

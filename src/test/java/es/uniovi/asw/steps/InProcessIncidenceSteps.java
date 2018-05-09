package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InProcessIncidenceSteps {

	WebDriver driver = new FirefoxDriver();
	
	@When("^he in process the incidence$")
	public void he_in_process_the_incidence() throws Throwable {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
	}

	
	@Then("^the incidence is in processed$")
	public void processed() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.IN_PROCESS));
		driver.quit();
	}
	
}

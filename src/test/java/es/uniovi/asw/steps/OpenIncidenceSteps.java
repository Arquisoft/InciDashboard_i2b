package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OpenIncidenceSteps {

	
	WebDriver driver = new FirefoxDriver();
	
	@When("^he opens the incidence$")
	public void he_opens_the_incidence() throws Throwable {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
	}
	
	@Then("^the incidence is opened$")
	public void opened() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.OPEN));
		driver.quit();
	}
	
}

package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CheckIncidenceEmptySteps {


	@And("^My incidences are zero")
	public void zeroIncidentes() {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
	}

	WebDriver driver = new FirefoxDriver();
	
	@Then("^There are not incidences")
	public void incidences() {
		
		if (driver.getCurrentUrl().equalsIgnoreCase("http://localhost:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
		driver.quit();
	}
	
}

package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;

public class CheckIncidenceSteps {

	private static WebDriver driver = new FirefoxDriver();



	@Then("^I can see my incidences")
	public void incidences() {
		driver.quit();
	}
	
}

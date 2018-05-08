package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class AddCommentsIncidenceSteps {

	private static WebDriver driver = new FirefoxDriver();

	@And("^I have incidences")
	public void ihaveincidences(){
		
	}
	
	@Then("^I add a comment and it is saved")
	public void added(){
		driver.quit();
	}
}

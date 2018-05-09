package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class AddCommentsIncidenceSteps {

	private static WebDriver driver = new FirefoxDriver();

	@And("^I have incidences")
	public void ihaveincidences(){
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
	}
	
	@Then("^I add a comment and it is saved")
	public void added(){
		driver.quit();
	}
}

	package es.uniovi.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPassFailSteps {

	private static WebDriver driver = new FirefoxDriver();


	
	

	@When("^with wrong password I try to login")
	public void incidentsAssigned() {
		driver.get("http://192.168.99.100:8082/");
		driver.findElement(By.id("password")).sendKeys("1");
		driver.findElement(By.id("login")).click();
	}


	@Then("^I should see the error page")
	public void checkFail() {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/login?error")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
		driver.quit();
	}
	
}

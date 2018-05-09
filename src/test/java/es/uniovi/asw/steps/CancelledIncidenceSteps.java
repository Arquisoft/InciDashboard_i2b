package es.uniovi.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CancelledIncidenceSteps {

	

	private WebDriver driver = new FirefoxDriver();
	private static String URL = "http://192.168.99.100:8082";
	
	@Given("^operator with email \"([^\"]*)\"$")
	public void operator_with_email(String arg1) throws Throwable {
		driver.navigate().to(URL);
		driver.get("http://192.168.99.100:8082");
		driver.findElement(By.id("email")).sendKeys("operator1@dashboard.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
	}
	
	@Given("^the first of his assigned incidences$")
	public void the_first_of_his_assigned_incidences() throws Throwable {
	    System.out.println("First incidence");
	}
	
	@When("^he cancels the incidence$")
	public void cancelledincidence() throws Throwable {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
	}
	
	@Then("^the incidence is cancelled$")
	public void cancelled() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.CANCELLED));
		driver.quit();
	}
	
}

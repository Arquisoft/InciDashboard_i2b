package es.uniovi.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import com.uniovi.main.InciDashboardI2bApplication;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest( classes = InciDashboardI2bApplication.class)
public class LoginSteps {

	//static String PathFirefox = "C:\\Firefox46.win\\FirefoxPortable.exe";
	private static String gecko = "drivers/geckodriver.exe";
	private static String URL = "http://192.168.99.100:8082";
	private static WebDriver driver;

	@Before
	public void init(){
		System.setProperty("webdriver.gecko.driver", gecko);
	}
	

	@Given("^I am a correct operator")
	public void logged() {
		//System.setProperty("webdriver.chrome.driver", gecko);
	    driver = new FirefoxDriver();
		//driver.navigate().to(URL);
		driver.navigate().to(URL);
		driver.get("http://192.168.99.100:8082");
		driver.findElement(By.id("email")).sendKeys("operator1@dashboard.com");
	}

	@When("^I login with a user")
	public void incidentsAssigned() {
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
	}


	@Then("^I should see the dashboard page")
	public void checkFail() {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
		driver.quit();
	}
}

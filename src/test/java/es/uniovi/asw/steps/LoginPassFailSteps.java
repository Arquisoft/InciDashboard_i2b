	package es.uniovi.asw.steps;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPassFailSteps {

	//static String PathFirefox = "C:\\Firefox46.win\\FirefoxPortable.exe";
	static String gecko = "drivers/geckodriver.exe";
	static String URL = "http://localhost:8082";
	static WebDriver driver = getDriver();

	@Before
	public static WebDriver getDriver() {
		// Firefox (Versión 46.0) sin geckodriver para Selenium 2.x.
		//System.setProperty("webdriver.gecko.driver", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@After
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}


	@Given("^I am not a correct operator")
	public void logged() {
		driver.get("http://localhost:8082");
		driver.findElement(By.id("email")).sendKeys("operator1@dashboard.com");
	}

	@When("^I try to login with wrong password")
	public void incidentsAssigned() {
		driver.findElement(By.id("pass")).sendKeys("1234567890");
		driver.findElement(By.id("login")).click();
	}


	@Then("^I should see the error page")
	public void checkFail() {
		if (driver.getCurrentUrl().equalsIgnoreCase("http://localhost:8082/login?error")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
		driver.close();
	}
}

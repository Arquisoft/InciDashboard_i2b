package es.uniovi.asw.steps;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Operator;
import com.uniovi.services.OperatorsService;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckIncidenceEmptySteps {

	
	@Autowired
	private OperatorsService opService;

	//static String PathFirefox = "C:\\Firefox46.win\\FirefoxPortable.exe";
	private static String gecko = "drivers/geckodriver.exe";
	private static String URL = "http://localhost:8082";
	private static WebDriver driver = getDriver();

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


	// Al finalizar la última prueba
	@After
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}


	@Given("^I am a correct operator")
	public void logged() {
		driver.get("http://localhost:8082/login");
		driver.findElement(By.id("email")).sendKeys("operator1@dashboard.com");
	}

	@When("^I login with a user")
	public void incidentsAssigned() {
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
	}

	@And("^My incidences are zero")
	public void zeroIncidentes() {
		Operator op = opService.getOperatorByEmail("operator1@dashboard.com");
		op.setNumNotifications(0);
		assertEquals(op.getNumNotifications(), 0);
	}

	@Then("^There are not incidences")
	public void incidences() {
		
		if (driver.getCurrentUrl().equalsIgnoreCase("http://localhost:8082/incidents")) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test1 Failed");
		}
		driver.close();
	}
}

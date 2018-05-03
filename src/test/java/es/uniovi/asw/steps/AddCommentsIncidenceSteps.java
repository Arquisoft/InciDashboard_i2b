package es.uniovi.asw.steps;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.uniovi.entities.Operator;
import com.uniovi.services.OperatorsService;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddCommentsIncidenceSteps {

	static String PathFirefox = "C:\\Firefox46.win\\FirefoxPortable.exe";
	static String gecko = "D:\\Descargas";
	static String URL = "http://localhost:8082/login";
	static WebDriver driver = getDriver(PathFirefox);

	private MockMvc mockMvc;
	
	@Autowired
	OperatorsService opService;

	@Before
	public static WebDriver getDriver(String PathFirefox) {
		// Firefox (Versión 46.0) sin geckodriver para Selenium 2.x.
		System.setProperty("webdriver.gecko.driver", PathFirefox);
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

	@And("^I have incidences")
	public void zeroIncidentes() {
		Operator op = opService.getOperatorByEmail("operator1@dashboard.com");
		assertEquals(op.getNumNotifications() >= 0, true);
	}

	@Then("^I add a comment and it is saved")
	public void i_can_See_my_incidences() throws Exception {
		MockHttpServletRequestBuilder request = post("/incident/addComment")
				.param("name", "good").param("comment", "test");

		String response = mockMvc.perform(request).andReturn().getResponse()
				.getContentAsString();
		assertEquals("Comment added", response);

		request = post("/incident/addComment").param("name", "bad")
				.param("comment", "test");

		response = mockMvc.perform(request).andReturn().getResponse()
				.getContentAsString();
		assertEquals("Error adding comment!", response);
	}

}

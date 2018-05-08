package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;

public class CheckIncidenceEmptySteps {

	/*
	@Autowired
	private OperatorsService opService;

	//static String PathFirefox = "C:\\Firefox46.win\\FirefoxPortable.exe";
	private static String gecko = "drivers/geckodriver.exe";
	private static String URL = "http://192.168.99.100:8082";
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




	@And("^My incidences are zero")
	public void zeroIncidentes() {
		Operator op = opService.getOperatorByEmail("operator1@dashboard.com");
		op.setNumNotifications(0);
		assertEquals(op.getNumNotifications(), 0);
	}
*/
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

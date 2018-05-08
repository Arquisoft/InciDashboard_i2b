package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;

public class CancelledIncidenceSteps {
/*
	@Autowired
	private IncidentsService inciService;
	
	@Autowired
	private OperatorsService opService;
	
	private Incident incidence;
	private Operator operator;
	
	
	


	@When("^he cancels the incidence$")
	public void cancelledincidence() throws Throwable {
		inciService.changeState(incidence.getInciName(), IncidentState.CANCELLED.toString());
	}
*/
	WebDriver driver = new FirefoxDriver();
	
	@Then("^the incidence is cancelled$")
	public void cancelled() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.CANCELLED));
		driver.quit();
	}
	
}

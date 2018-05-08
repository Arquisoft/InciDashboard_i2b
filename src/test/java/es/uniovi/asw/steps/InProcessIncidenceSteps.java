package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;

public class InProcessIncidenceSteps {
/*
	@Autowired
	private IncidentsService inciService;
	
	@Autowired
	private OperatorsService opService;
	
	private Incident incidence;
	private Operator operator;
	
	

	

	@When("^he in process the incidence$")
	public void inprocessincidence() throws Throwable {
		inciService.changeState(incidence.getInciName(), IncidentState.IN_PROCESS.toString());
	}
*/
	WebDriver driver = new FirefoxDriver();
	
	@Then("^the incidence is in processed$")
	public void processed() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.IN_PROCESS));
		driver.quit();
	}
	
}

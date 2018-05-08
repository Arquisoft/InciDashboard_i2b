package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;

public class OpenIncidenceSteps {
/*
	@Autowired
	private IncidentsService inciService;
	
	@Autowired
	private OperatorsService opService;
	
	private Incident incidence;
	private Operator operator;
	
	@Given("^operator with email \"([^\"]*)\"$")
	public void openidentificador(String operatorIdentifier) throws Throwable {
		this.operator = opService.getOperatorByEmail(operatorIdentifier);
	}

	@Given("^the first of his assigned incidences$")
	public void openincidences() throws Throwable {
		incidence = inciService.getIncidentsOf(operator).get(0);
	}

	@When("^he opens the incidence$")
	public void openincidence() throws Throwable {
		inciService.changeState(incidence.getInciName(), IncidentState.OPEN.toString());
	}
*/
	
	WebDriver driver = new FirefoxDriver();
	
	@Then("^the incidence is opened$")
	public void opened() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.OPEN));
		driver.quit();
	}
	
}

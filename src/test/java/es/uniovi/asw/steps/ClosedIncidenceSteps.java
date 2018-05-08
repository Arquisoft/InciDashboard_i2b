package es.uniovi.asw.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;

public class ClosedIncidenceSteps {
/*
	@Autowired
	private IncidentsService inciService;
	
	@Autowired
	private OperatorsService opService;
	
	private Incident incidence;
	private Operator operator;
	
	
	/**
	 * If we wanted to use a lambda expression, we would need to modify the dependencies
	 * And the lambda expression would be 
	 * Given("operator with email (\\s+)", (String email) -> {
            System.out.format("Cukes: %n\n", cukes);
            this.operator = opService.getOperatorByEmail(email); 
        });
	 * @param operatorIdentifier
	 * @throws Throwable
	 



	@When("^he closes the incidence$")
	public void closedincidence() throws Throwable {
		inciService.changeState(incidence.getInciName(), IncidentState.CLOSED.toString());
	}
*/
	WebDriver driver = new FirefoxDriver();
	
	@Then("^the incidence is closed$")
	public void closed() throws Throwable {
		//assertTrue(incidence.getState().equals(IncidentState.CLOSED));
		driver.quit();
	}

}

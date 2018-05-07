package es.uniovi.asw.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Incident;
import com.uniovi.entities.IncidentState;
import com.uniovi.entities.Operator;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorsService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OpenIncidenceSteps {

	@Autowired
	private IncidentsService inciService;
	
	@Autowired
	private OperatorsService opService;
	
	private Incident incidence;
	private Operator operator;
	
	@Given("^operator with email \"([^\"]*)\"$")
	public void identificador(String operatorIdentifier) throws Throwable {
		this.operator = opService.getOperatorByEmail(operatorIdentifier);
	}

	@Given("^the first of his assigned incidences$")
	public void incidences() throws Throwable {
		incidence = inciService.getIncidentsOf(operator).get(0);
	}

	@When("^he opens the incidence$")
	public void incidence() throws Throwable {
		inciService.changeState(incidence.getInciName(), IncidentState.OPEN.toString());
	}

	@Then("^the incidence is opened$")
	public void opened() throws Throwable {
		assertTrue(incidence.getState().equals(IncidentState.OPEN));
	}
}

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

public class ClosedIncidenceSteps {

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
	 */
	@Given("^operator with email \"([^\"]*)\"$")
	public void el_operario_con_identificador(String operatorIdentifier) throws Throwable {
		this.operator = opService.getOperatorByEmail(operatorIdentifier);
	}

	@Given("^the first of his assigned incidences$")
	public void the_first_of_his_assigned_incidences() throws Throwable {
		incidence = inciService.getIncidentsOf(operator).get(0);
	}

	@When("^he closes the incidence$")
	public void he_closes_the_incidence() throws Throwable {
		inciService.changeState(incidence.getInciName(), IncidentState.CLOSED.toString());
	}

	@Then("^the incidence is closed$")
	public void the_incidence_is_closed() throws Throwable {
		assertTrue(incidence.getState().equals(IncidentState.CLOSED));
	}

}

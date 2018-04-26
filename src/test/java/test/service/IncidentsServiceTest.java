package test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.entities.AgentInfo;
import com.uniovi.entities.Incident;
import com.uniovi.entities.IncidentState;
import com.uniovi.entities.Operator;
import com.uniovi.entities.location.LatLng;
import com.uniovi.main.InciDashboardI2bApplication;
import com.uniovi.services.AgentsService;
import com.uniovi.services.IncidentsService;

@SpringBootTest(classes = { InciDashboardI2bApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class IncidentsServiceTest {
	
	@ClassRule 
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "incidents");

	@Autowired
	private IncidentsService incidentsService;

	@Autowired
	private AgentsService agentsService;
	
	private Incident inciTest1;
	private Incident inciTest2;
	private Incident inciTest3;
	
	private AgentInfo testInfo1;
	private AgentInfo testInfo2;
	private AgentInfo testInfo3;
	
	@Before
	public void setUp() {
		testInfo1 = new AgentInfo("agentTest1", "pruebas123", "Person");
		testInfo2 = new AgentInfo("agentTest2", "pruebas123", "Entity");
		testInfo3 = new AgentInfo("agentTest3", "pruebas123", "Sensor");

		inciTest1 = new Incident("inciTest1", new LatLng(10, 12), testInfo1);
		inciTest1.getProperties().put("pollution", 15.0);
		inciTest2 = new Incident("inciTest2", new LatLng(52, 42), testInfo2);
		inciTest2.getProperties().put("temperature", 20);
		inciTest2.setLocation(null);
		inciTest3 = new Incident("inciTest3", new LatLng(25, 25), testInfo3);

		agentsService.addAgent(testInfo1);
		agentsService.addAgent(testInfo2);
		agentsService.addAgent(testInfo3);
		
		incidentsService.addIncident(inciTest1);
		incidentsService.addIncident(inciTest2);
		incidentsService.addIncident(inciTest3);
	}
	
	@After
	public void clean() {
		if (testInfo1 != null)
			agentsService.deleteAgent(testInfo1);
		if (testInfo2 != null)
			agentsService.deleteAgent(testInfo2);
		if (testInfo3 != null)
			agentsService.deleteAgent(testInfo3);
		
		incidentsService.deleteIncidentByName("inciTest1");
		incidentsService.deleteIncidentById(inciTest2.getId());
		incidentsService.deleteIncidentById(inciTest3.getId());
	}

	@Test
	public void testIncidentCRUD() {
		// retrieve
		assertEquals(incidentsService.getIncidentByName("inciTest1"), inciTest1);
		assertEquals(incidentsService.getIncidentByName("inciTest2"), inciTest2);
		assertEquals(incidentsService.getIncidentByName("inciTest3"), inciTest3);

		// create
		Incident inciTest4 = new Incident("inciTest4", new LatLng(10, 20), testInfo3);
		assertEquals(incidentsService.getIncidentByName("inciTest4"), null);
		incidentsService.addIncident(inciTest4);
		assertEquals(incidentsService.getIncidentByName("inciTest4"), inciTest4);
		
		// update
		assertEquals(0, incidentsService.getIncidentByName("inciTest4").getMoreInfo().size());
		inciTest4.addMoreInfo("Hellooooo");
		incidentsService.addIncident(inciTest4);
		assertEquals(1, incidentsService.getIncidentByName("inciTest4").getMoreInfo().size());
		
		// delete
		incidentsService.deleteIncidentByName("inciTest4");
		assertEquals(incidentsService.getIncidentByName("inciTest4"), null);
	}
	
	@Test
	public void testTemperatureIncidents() {
		assertTrue(incidentsService.getTemperatureSensorIncidents().contains(inciTest2));
		assertFalse(incidentsService.getTemperatureSensorIncidents().contains(inciTest1));
	}
	
	@Test
	public void testGeolocatedIncidents() {
		assertTrue(incidentsService.getGeolocatedIncidents().contains(inciTest1));
		assertFalse(incidentsService.getGeolocatedIncidents().contains(inciTest2));
	}
	
	@Test
	public void testByKind() {
		assertTrue(incidentsService.getKindIncidents("Person").contains(inciTest1));
		assertFalse(incidentsService.getKindIncidents("Person").contains(inciTest2));
		assertFalse(incidentsService.getKindIncidents("Person").contains(inciTest3));
		
		assertFalse(incidentsService.getKindIncidents("Sensor").contains(inciTest1));
		assertFalse(incidentsService.getKindIncidents("Sensor").contains(inciTest2));
		assertTrue(incidentsService.getKindIncidents("Sensor").contains(inciTest3));
		
		assertFalse(incidentsService.getKindIncidents("Entity").contains(inciTest1));
		assertTrue(incidentsService.getKindIncidents("Entity").contains(inciTest2));
		assertFalse(incidentsService.getKindIncidents("Entity").contains(inciTest3));
	}
	
	@Test
	public void testAvailableStates() {
		List<String> statesReceived = incidentsService.getAvailableStates();
		IncidentState[] realStates = IncidentState.values();
		for (int i = 0; i < realStates.length; i++) {
			assertTrue(statesReceived.contains(realStates[i].toString()));
		}
		assertEquals(realStates.length, statesReceived.size());
	}
	
	@Test
	public void testGetIncidentsOfOperator() {
		Operator op = new Operator("paco", "123456", "ROLE_OPERATOR");
		List<Incident> incidents = incidentsService.getIncidentsOf(op);
		assertEquals(0, incidents.size());
		
		inciTest1.assignOperator(op);
		inciTest3.assignOperator(op);
		incidentsService.addIncident(inciTest1);
		incidentsService.addIncident(inciTest3);
		
		incidents = incidentsService.getIncidentsOf(op);
		assertEquals(2, incidents.size());
		assertTrue(incidents.contains(inciTest1));
		assertTrue(incidents.contains(inciTest3));
	}
	
	@Test
	public void testChangeState() {
		incidentsService.changeState(inciTest1.getInciName(), IncidentState.OPEN.toString());
		assertEquals(IncidentState.OPEN, incidentsService.getIncidentByName("inciTest1").getState());
		
		incidentsService.changeState("notAnIncident", IncidentState.CLOSED.toString());
		assertNotEquals(IncidentState.CLOSED, incidentsService.getIncidentByName("inciTest1").getState());
		

		incidentsService.changeState(inciTest1.getInciName(), "notAState");
		assertEquals(IncidentState.OPEN, incidentsService.getIncidentByName("inciTest1").getState());
	}

}

package test.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.uniovi.entities.AgentInfo;
import com.uniovi.entities.Incident;
import com.uniovi.util.RandomIncidentGenerator;

public class IncidentGeneratorTest {
	
	private RandomIncidentGenerator generator;
	private List<AgentInfo> agents;
	
	@Before
	public void setUp() {
		agents = new ArrayList<AgentInfo>();
		agents.add(new AgentInfo("Son", "123456", "Person"));
		agents.add(new AgentInfo("MVX_!24", "admin", "Sensor"));
		agents.add(new AgentInfo("Jose", "pass23", "Entity"));
		agents.add(new AgentInfo("Paco", "pass43", "Person"));
		
		generator = new RandomIncidentGenerator();
		generator.setPossibleAgents(agents);
	}
	
	@Test
	public void testGenerator() {
		for (int i = 0; i < 50; i++) {
			Incident incident = generator.generateRandomIncident();
			assertTrue(agents.contains(incident.getAgent()));
			
			// assert valid location
			assertTrue(incident.getLocation().latitude > -90);
			assertTrue(incident.getLocation().latitude < 90);
			assertTrue(incident.getLocation().longitude > -180);
			assertTrue(incident.getLocation().longitude < 180);
			
			if ("Sensor".equals(incident.getAgent().getKind())) {
				assertTrue(incident.getProperties().containsKey("temperature"));
			} else {
				assertFalse(incident.getProperties().containsKey("temperature"));
			}
		}
	}

}

package test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.entities.Incident;
import com.uniovi.main.InciDashboardI2bApplication;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.InsertTestDataService;
import com.uniovi.services.OperatorsService;

@SpringBootTest(classes = { InciDashboardI2bApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class InsertSampleDataServiceTest {

	@Autowired
	private IncidentsService incidentsService;
	
	@Autowired
	private InsertTestDataService sampleDataService;

	@Autowired
	private OperatorsService operatorsService;
	
	@Test
	public void testSampleData() throws Exception {
		sampleDataService.deleteAll();
		sampleDataService.init();
		
		//Start data inserted
		List<Incident> incidents = incidentsService.getAllIncidents();
		assertEquals(InsertTestDataService.NUM_INCIDENTS, incidents.size());
		
		assertNotNull(operatorsService.getOperatorByEmail("operator1@dashboard.com"));
		assertNotNull(operatorsService.getOperatorByEmail("operator2@dashboard.com"));
		assertNotNull(operatorsService.getOperatorByEmail("operator3@dashboard.com"));
	}

}

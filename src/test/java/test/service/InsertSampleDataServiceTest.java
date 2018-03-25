package test.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		assertNotNull(incidentsService.getIncidentByName("inci1"));
		assertNotNull(incidentsService.getIncidentByName("inci2"));
		assertNotNull(incidentsService.getIncidentByName("inci3"));
		assertNotNull(incidentsService.getIncidentByName("inci4"));
		assertNotNull(incidentsService.getIncidentByName("inci5"));
		
		assertNotNull(operatorsService.getOperatorByEmail("pacoo"));
		assertNotNull(operatorsService.getOperatorByEmail("pacoo"));
		assertNotNull(operatorsService.getOperatorByEmail("pacoo"));
	}

}

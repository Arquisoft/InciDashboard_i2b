package test.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.entities.Operator;
import com.uniovi.main.InciDashboardI2bApplication;
import com.uniovi.services.OperatorsService;

@SpringBootTest(classes = { InciDashboardI2bApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class OperatorsServiceTest {

	@ClassRule 
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "incidents");
	
	@Autowired
	private OperatorsService operatorService;

	private Operator testOp1;
	private Operator testOp2;
	private Operator testOp3;

	@Before
	public void setUp() {
		testOp1 = new Operator("pacoo@dashboard.com", "Paco", "123456", "ROLE_OPERATOR");
		testOp2 = new Operator("david_son@dashboard.com", "Sonny", "pass123", "ROLE_ADMIN");
		testOp3 = new Operator("miEmail@dashboard.com", "Antonio", "caballoBorrado", "ROLE_OPERATOR");

		operatorService.addOperator(testOp1);
		operatorService.addOperator(testOp2);
		operatorService.addOperator(testOp3);
	}

	@After
	public void clean() {
		operatorService.deleteOperator(testOp1);
		operatorService.deleteOperator(testOp2);
		operatorService.deleteOperator(testOp3);
	}

	@Test
	public void testOperatorCRUD() {
		// retrieve
		assertEquals(operatorService.getOperatorByEmail("pacoo@dashboard.com"), testOp1);
		assertEquals(operatorService.getOperatorByEmail("david_son@dashboard.com"), testOp2);
		assertEquals(operatorService.getOperatorByEmail("miEmail@dashboard.com"), testOp3);

		// create
		Operator testOp4 = new Operator("new@dashboard.com", "Lucia", "123456", "ROLE_OPERATOR");
		assertEquals(operatorService.getOperatorByEmail("new@dashboard.com"), null);
		operatorService.addOperator(testOp4);
		assertEquals(operatorService.getOperatorByEmail("new@dashboard.com"), testOp4);

		// update
		assertEquals("Lucia", operatorService.getOperatorByEmail("new@dashboard.com").getOperatorname());
		testOp4.setOperatorname("Cambio de nombre");
		operatorService.addOperator(testOp4);
		assertEquals("Cambio de nombre",
				operatorService.getOperatorByEmail("new@dashboard.com").getOperatorname());

		// delete
		operatorService.deleteOperator(testOp4);
		assertEquals(operatorService.getOperatorByEmail("new@dashboard.com"), null);
	}

}

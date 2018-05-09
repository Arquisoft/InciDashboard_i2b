package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.entities.Incident;
import com.uniovi.entities.Operator;
import com.uniovi.main.InciDashboardI2bApplication;

@SpringBootTest(classes= {
		InciDashboardI2bApplication.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public class OperatorTest {
	
	private Operator op1 = new Operator("eleven@dashboard.com", "eleven", "ROLE_OPERATOR");
	
	@Test
	public void testInstantiation() {
		assertEquals("eleven@dashboard.com", op1.getEmail());
		assertEquals("eleven", op1.getOperatorname());
		assertEquals(false, op1.getIsAdmin());
		assertEquals("ROLE_OPERATOR", op1.getRole());
	}

	@Test
	public void testEquals() {
		Operator op2 = new Operator("eleven@dashboard.com", "twelve", "ROLE_ADMIN");
		Operator op3 = new Operator(new ObjectId(), "twelve@dashboard.com", "eleven", "ROLE_OPERATOR");
		Operator op4 = new Operator("thirteen@dashboard.com", "thirteen", "ROLE_OPERATOR");
		Operator op5 = new Operator();
		
		assertEquals(op1, op2);
		assertNotEquals(op1, op3);
		assertNotEquals(op1, op4);
		assertNotEquals(op1, op5);
		
		assertEquals(op1.hashCode(), op2.hashCode());
		assertNotEquals(op1.hashCode(), op3.hashCode());
		assertNotEquals(op1.hashCode(), op4.hashCode());
		assertNotEquals(op1.hashCode(), op5.hashCode());
		
		// strange cases
		Operator op6 = op2;
		assertEquals(op6, op2);
		
		assertNotEquals(op5, new Incident());
		assertNotEquals(null, op5);
		
		op2.setEmail(null);
		assertNotEquals(op2, op4);
	}
	
	
	@Test
	public void testToString() {
		String toStringOp = "Operator [id=null, email=eleven@dashboard.com, password=null, operatorname=eleven, role=ROLE_OPERATOR, numNotifications=0, mapAccess=true, chartAccess=true, incidentModify=true, admin=false]";
		assertEquals(toStringOp, op1.toString());
		
		Operator op2 = new Operator("cleopatra@gob.eg", "cleo", "ROLE_ADMIN");
		toStringOp = "Operator [id=null, email=cleopatra@gob.eg, password=null, operatorname=cleo, role=ROLE_ADMIN, numNotifications=0, mapAccess=true, chartAccess=true, incidentModify=true, admin=false]";
		assertEquals(toStringOp, op2.toString());
		
		op2.setEmail("cleopatra@dead.world");
		assertEquals("Operator [id=null, email=cleopatra@dead.world, password=null, operatorname=cleo, role=ROLE_ADMIN, numNotifications=0, mapAccess=true, chartAccess=true, incidentModify=true, admin=false]", op2.toString());
		
		op2.setIsAdmin(false);
		assertEquals("Operator [id=null, email=cleopatra@dead.world, password=null, operatorname=cleo, role=ROLE_OPERATOR, numNotifications=0, mapAccess=true, chartAccess=true, incidentModify=true, admin=false]", op2.toString());
		
		op2.setOperatorname("CLEOPATRA");
		assertEquals("Operator [id=null, email=cleopatra@dead.world, password=null, operatorname=CLEOPATRA, role=ROLE_OPERATOR, numNotifications=0, mapAccess=true, chartAccess=true, incidentModify=true, admin=false]", op2.toString());
	}
	
	
	@Test
	public void testNumNotifications() {
		Operator op2 = new Operator("eleven@dashboard.com", "twelve", "ROLE_ADMIN");
		op2.setNumNotifications(2);
		assertEquals(2, op2.getNumNotifications());
		op2.setNumNotifications(5);
		assertEquals(5, op2.getNumNotifications());
	}
	
	@Test
	public void testOperatorPermissions() {
		Operator op2 = new Operator("eleven@dashboard.com", "twelve", "ROLE_ADMIN");
		assertTrue(op2.isAdmin());
		assertTrue(op2.isChartAccess());
		assertTrue(op2.isIncidentModify());
		assertTrue(op2.isMapAccess());
		op2.setIsAdmin(false);
		op2.setMapAccess(false);
		op2.setChartAccess(false);
		op2.setIncidentModify(false);
		assertFalse(op2.isAdmin());
		assertFalse(op2.isChartAccess());
		assertFalse(op2.isIncidentModify());
		assertFalse(op2.isMapAccess());
	}
}

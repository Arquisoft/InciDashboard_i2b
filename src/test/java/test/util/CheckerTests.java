package test.util;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.uniovi.util.Checker;

public class CheckerTests {
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsEmpty() {
		Checker.isEmpty("notempty");
		Checker.isEmpty("");
		//If the exception is not thrown fail
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsNull() {
		Checker.isNull(new String());
		Checker.isNull(null);
		//If the exception is not thrown fail
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)	
	public void testIsGreaterThanOrEqualToZero() {
		Checker.isGreaterThanOrEqualToZero(5);
		Checker.isGreaterThanOrEqualToZero(0);
		
		Checker.isGreaterThanOrEqualToZero(-1);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsLowerThanZero() {
		Checker.isGreaterThanOrEqualToZero(-1);
		//If the exception is not thrown fail
		fail();
	}
}
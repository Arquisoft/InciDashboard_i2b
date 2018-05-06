package test.util;

import org.junit.Test;

import com.uniovi.util.Checker;

public class CheckerTests {
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsEmpty() {
		Checker.isEmpty("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsNull() {
		Checker.isNull(null);
	}
	
	@Test
	public void testIsNotEmpty() {
		Checker.isEmpty("notempty");
	}
	
	@Test
	public void testIsNotNull() {
		Checker.isNull(new String());
	}
}
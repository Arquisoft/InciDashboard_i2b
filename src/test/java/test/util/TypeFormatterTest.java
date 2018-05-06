package test.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.uniovi.util.TypeFormatter;

public class TypeFormatterTest {

	@Test
	public void testTimestampToDateString() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		long stamp = date.getTime();
		stamp /= 1000;
		int year = cal.get(Calendar.YEAR);
		// JANUARY IS 0
		int month = cal.get(Calendar.MONTH);
		month++;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		String result = year + addZeroLeft(month) + addZeroLeft(day) + addZeroLeft(hour) + addZeroLeft(minute)
				+ addZeroLeft(sec);
		assertEquals(TypeFormatter.timestampToDateString(stamp), result);
	}

	private String addZeroLeft(int number) {
		String str;
		if (number < 10) {
			str = "0" + number;
		} else {
			str = "" + number;
		}
		return str;
	}
}

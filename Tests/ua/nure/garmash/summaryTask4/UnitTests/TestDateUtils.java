package ua.nure.garmash.summaryTask4.UnitTests;

import Utils.DateUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TestDateUtils {
    @Test
    public void testDateFormat() {
        assertEquals("01-01-2018", DateUtils.formatDate("2018-01-01",
                "yyyy-MM-dd", "dd-MM-yyyy"));
    }

    @Test(expected = NullPointerException.class)
    public void testDateFormatException() {
        DateUtils.formatDate(null,
                "yyyy-MM-dd", "dd-MM-yyyy");
    }

    @Test
    public void TestDatesOverlap() {
        assertFalse(DateUtils.datesOverlap("2008-01-01", "2008-01-03",
                "2008-01-04", "2008-01-05"));
        assertTrue(DateUtils.datesOverlap("2008-01-01", "2008-01-03",
                "2008-01-03", "2008-01-05"));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test (expected = NullPointerException.class)
    public void testDatesOverlap() {
        DateUtils.datesOverlap(null, "2008-01-03",
                "2008-01-03", "2008-01-05");
        DateUtils.datesOverlap("", "2008-01-03",
                "2008-01-03", "2008-01-05");

    }

    java.sql.Date date;
    java.sql.Date expectedDate;

    @Before
    public void prepareDate()  {
        date = new java.sql.Date(2008, 1, 1);
        expectedDate = DateUtils.add2Days(date);
    }


    @Test
    public void addDaysTest() {
        assertEquals(expectedDate, DateUtils.add2Days(date));
        assertNotEquals(date,DateUtils.add2Days(date));
    }
    @Test(expected = NullPointerException.class)
    public void addDaysTestException() {
        DateUtils.add2Days(null);
    }
}

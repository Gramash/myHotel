package ua.nure.garmash.summaryTask4.UnitTests;

import Utils.EmailUtils;
import org.junit.Ignore;
import org.junit.Test;

public class EmailTests {
    @Test
    @Ignore
    public void testEmailUtils() {
        EmailUtils.send("garmash.g.k@gmail.com", "subj", "message");
    }
}

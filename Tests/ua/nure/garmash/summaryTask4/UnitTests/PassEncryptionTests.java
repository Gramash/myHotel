package ua.nure.garmash.summaryTask4.UnitTests;

import Utils.PasswordEncryption;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PassEncryptionTests {

    @Test (expected = IllegalStateException.class)
    public void testPasswordHashException ()  {
        PasswordEncryption.check(null, "pass");
        PasswordEncryption.check("", "pass");

    }

    @Test
    public void testPasswordCheck () throws NoSuchAlgorithmException {
        assertTrue(PasswordEncryption.check("pass",
                PasswordEncryption.getSaltedHash("pass")));
        assertFalse(PasswordEncryption.check("pazz",
                PasswordEncryption.getSaltedHash("pass")));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPassHash () {
        byte [] salt = new byte[0];
        PasswordEncryption.hash("", salt);
    }




}

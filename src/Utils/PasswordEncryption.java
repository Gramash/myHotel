package Utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordEncryption {
    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int iterations = 20 * 1000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;

    /**
     * Computes a salted PBKDF2 hash of given plaintext password
     * suitable for storing in a database.
     * Empty passwords are not supported.
     */
    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
    }

    /**
     * Checks whether given plaintext password corresponds
     * to a stored salted hash of the password.
     */
    public static boolean check(String password, String stored)  {
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                    "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

//    public static String (String password, String stored) throws Exception {
//        String[] saltAndPass = stored.split("\\$");
//        if (saltAndPass.length != 2) {
//            throw new IllegalStateException(
//                    "The stored password have the form 'salt$hash'");
//        }
//        String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));
//        return hashOfInput.equals(saltAndPass[1]);
//    }

    private static String hash(String password, byte[] salt) {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");

        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey key = null;
            key = f.generateSecret(new PBEKeySpec(
                    password.toCharArray(), salt, iterations, desiredKeyLen)
            );
            return Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (GeneralSecurityException e) {
            System.out.println("failed to hash pass");
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        String str = getSaltedHash("pass");
        System.out.println(str);
        System.out.println(check("pass", str));
    }
}

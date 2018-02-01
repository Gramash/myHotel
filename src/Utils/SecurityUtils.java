package Utils;


import config.SecurityConfig;

import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;


public class SecurityUtils {


    // Проверить требует ли данный 'request' входа в систему или нет.
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternsUtils.getUrlPattern(request);

        Set<String> roles = SecurityConfig.getAllRoles();

        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    // Check if request is made by user with appropriate role
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternsUtils.getUrlPattern(request);

        Set<String> allRoles = SecurityConfig.getAllRoles();

        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    private static byte[] hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest;
        digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        return digest.digest();
    }

    public static String getHexString(byte[] inputArray) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < inputArray.length; i++) {
            resultString.append(String.format("%02X ", inputArray[i]));
        }
        return resultString.toString();
    }

    public static void hashPass () throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = new byte[16];
        Random random = new Random();
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        System.out.printf("salt: %s%n", enc.encodeToString(salt));
        System.out.printf("hash: %s%n", enc.encodeToString(hash));
    }


    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {


    }
}
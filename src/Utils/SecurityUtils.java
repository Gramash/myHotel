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
        System.out.println("isSecurityPageMethod================>");
        String urlPattern = UrlPatternsUtils.getUrlPattern(request);
        System.out.println("url pattern = " + urlPattern);
        System.out.println("command = " + request.getParameter("command"));
        urlPattern = request.getParameter("command");
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
        urlPattern = request.getParameter("command");
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
}
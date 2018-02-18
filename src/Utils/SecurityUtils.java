package Utils;


import config.SecurityConfig;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


public class SecurityUtils {


    // Проверить требует ли данный 'request' входа в систему или нет.
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternsUtils.getUrlPattern(request);
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

        String urlPattern = request.getParameter("command");
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
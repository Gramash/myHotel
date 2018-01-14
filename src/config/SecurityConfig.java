package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_CUSTOMER = "CUSTOMER";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Congig for CUSTOMER role
        List<String> urlPatternsCustomer = new ArrayList<String>();

        urlPatternsCustomer.add("/userInfo");
        urlPatternsCustomer.add("/customerTask");

        mapConfig.put(ROLE_CUSTOMER, urlPatternsCustomer);

        // Config for MANAGER role
        List<String> urlPatternsManager = new ArrayList<String>();

        urlPatternsManager.add("/userInfo");
        urlPatternsManager.add("/managerTask");
        urlPatternsManager.add("/customerTask");

        mapConfig.put(ROLE_MANAGER, urlPatternsManager);
    }

    public static Set<String> getAllRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
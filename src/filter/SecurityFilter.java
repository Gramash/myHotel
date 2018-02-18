package filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MySQL.JavaBeans.UserAccount;
import Utils.AppUtils;
import Utils.SecurityUtils;
import Wrappers.UserRoleRequestWrapper;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SecurityFilter.class);

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("Filter starts");

        HttpServletRequest request = (HttpServletRequest) req;
        LOG.debug("Request URI ===> " + request.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) resp;

        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());


        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {

            String userLogin = loginedUser.getUserLogin();

            String role = loginedUser.getAccessLevel();

            // add user login and role to the current request
            wrapRequest = new UserRoleRequestWrapper(userLogin, role, request);
        }

        // pages that require user to be logged in
        if (SecurityUtils.isSecurityPage(request)) {

            //if user is not logged in yet ==> redirect to login.jsp
            if (loginedUser == null) {
                response.sendRedirect(request.getContextPath() + "/Views/loginView.jsp");
                return;
            }

            // Check if user has permission (correct role) to view the page
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getRequestDispatcher("/Views/accessDeniedForSure.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}

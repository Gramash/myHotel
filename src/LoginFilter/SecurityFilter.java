package LoginFilter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaBeans.UserAccount;
import Utils.AppUtils;
import Utils.SecurityUtils;
import Wrappers.UserRoleRequestWrapper;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // User info is saved to session (after he successfully logged in)
        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
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
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Check if user has permission (correct role) to view the page
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getRequestDispatcher("/WEB-INF/Views/accessDeniedView.jsp");

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

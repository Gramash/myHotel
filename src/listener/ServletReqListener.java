package listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class ServletReqListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        System.out.println("ServletRequest destroyed. Remote IP="+ servletRequest.getRequestURI());

    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        System.out.println("ServletRequest initialized. Remote IP="+servletRequest.getRequestURI());
    }
}

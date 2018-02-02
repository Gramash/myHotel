package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("[ContextListener]: servlet context  initialization starts ");
        System.out.println("[ContextListener]: servlet context  initialization finished ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

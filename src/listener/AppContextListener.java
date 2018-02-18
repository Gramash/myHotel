package listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener{
    private static final Logger LOG = Logger.getLogger(AppContextListener.class);



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("[ContextListener]: servlet context  initialization starts ");
        log("[ContextListener]: servlet context  initialization finished ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("[Context Listener]: Servlet context destruction starts");

        ServletContext servletContext = servletContextEvent.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();

        log("[Context Listener]: Servlet context destruction finished");

    }



    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(
                    servletContext.getRealPath("/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            log("Cannot configure Log4j");
            ex.printStackTrace();
        }
        log("Log4J initialization finished");
    }

    private void initCommandContainer() {

        try {
            Class.forName("ua.nure.your_last_name.SummaryTask4.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }


    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}

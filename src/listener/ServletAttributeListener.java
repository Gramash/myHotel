package listener;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("Servlet context attribute added:: {" + servletContextAttributeEvent.getName() + ", "+
        servletContextAttributeEvent.getValue());

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("Servlet context attribute removed:: {" + servletContextAttributeEvent.getName() + ", "+
                servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("Servlet context attribute replaced:: {" + servletContextAttributeEvent.getName() + ", "+
                servletContextAttributeEvent.getValue());
    }
}

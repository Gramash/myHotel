package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;

@WebListener
public class HttpSessionListener implements javax.servlet.http.HttpSessionListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        System.out.println("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        System.out.println("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
}

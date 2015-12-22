package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by zzt on 12/22/15.
 * <p>
 * Usage:
 */

/**
 * @deprecated not suitable for count all visitor
 * because when response is returned, request is destroyed
 */
//@WebListener()
public class AllListener implements ServletRequestListener {
    public static final String REQUEST = "request";
    private static int requested = 0;

    @Override
    public synchronized void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        requested--;
        setRequestNum(sre);
    }

    private void setRequestNum(ServletRequestEvent sre) {
        ServletContext servletContext = sre.getServletContext();
        servletContext.setAttribute(REQUEST, requested);
    }

    @Override
    public synchronized void requestInitialized(ServletRequestEvent sre) {
        requested++;
        setRequestNum(sre);
    }

}

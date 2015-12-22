package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by zzt on 12/16/15.
 * <p>
 * Usage:
 */
@WebListener
public class LoginCounter implements HttpSessionListener {
    public static final String LOGGED_NUM = "LoggedNum";
    private static int loggedCount = 0;

    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        loggedCount++;
        setLoggedNum(se);
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
        loggedCount--;
        setLoggedNum(se);
    }

    public void setLoggedNum(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        // send count info by global variable
        session.setAttribute(LOGGED_NUM, loggedCount);
    }
}

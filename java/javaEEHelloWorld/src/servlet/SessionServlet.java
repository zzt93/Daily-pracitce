package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zzt on 12/10/15.
 * <p>
 * Usage:
 */

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<head><title>" + "SessionServlet Output</title></head><body>");
        out.println("<h1>SessionServlet Output</h1>");
        Integer ival = (Integer) session.getAttribute("sessiontest.counter");
        if (ival == null) {
            ival = 1;
        } else {
            ival++;
        }
        session.setAttribute("sessiontest.counter", ival);
        out.println("You have hit this page <b>" + ival + "</b> times.<p>");
        out.println("Click <a href=" + req.getContextPath() + resp.encodeURL("/SessionServlet") + ">here</a>");
        out.println(" to ensure that session tracking is working even if cookies aren't supported. ");
        out.println("<h3>Session Data:</h3>");
        out.println("Session ID in Request: " + req.getRequestedSessionId());
        out.println("<br>Session ID in Request from Cookie: " + req.isRequestedSessionIdFromCookie());
        out.println("<br>Session ID in Request from URL: " + req.isRequestedSessionIdFromURL());
        out.println("New Session: " + session.isNew());
        out.println("<br>Session ID: " + session.getId());
        out.println("<br>Creation Time: " + session.getCreationTime());
        out.println("<br>Last Accessed Time: " + session.getLastAccessedTime());

    }
}

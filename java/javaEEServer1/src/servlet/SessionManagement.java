package servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zzt on 12/28/15.
 * <p>
 * Usage:
 */
public class SessionManagement {

    public static void setSession(HttpServletRequest request, int sid) {
        HttpSession session = request.getSession(true);
        session.setAttribute(LoginServlet.SID, sid);
    }

    /**
     * if this method return false, the response should be considered to be committed and should not be written to.
     *
     * @param request Assume httpServletRequest
     * @param response Assume httpServletResponse
     *
     * @return whether there is a user login created session
     *
     * @throws IOException
     */
    public static boolean checkSession(ServletRequest request, ServletResponse response) throws IOException {

        if (request instanceof HttpServletRequest
                && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute(LoginServlet.SID) == null) {
                resp.sendRedirect(req.getContextPath() + "/html/login.html");
                return false;
            }
            return true;
        }
        return false;
    }
}

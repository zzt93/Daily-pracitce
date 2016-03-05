package interceptor;

import action.InnerLogin;
import action.UserLogin;
import org.apache.struts2.ServletActionContext;

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

    public static void setUserSession(int uid) {
        setSession(UserLogin.UID, uid);
    }

    public static void setStaffSession(int sid) {
        setSession(InnerLogin.SID, sid);
    }

    private static void setSession(String s, int id) {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        session.setAttribute(s, id);
    }

    public static HttpSession getSession() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getSession(false);
    }

    public static int getUid() {
        HttpSession session = getSession();
        return (int) session.getAttribute(UserLogin.UID);
    }


    public static boolean checkId(String id) {
        System.out.println("Interceptor Fired");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);

        return session == null || session.getAttribute(id) == null;
    }
}

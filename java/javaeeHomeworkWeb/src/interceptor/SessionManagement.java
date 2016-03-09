package interceptor;

import action.InnerLogin;
import action.UserLogin;
import entity.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zzt on 12/28/15.
 * <p>
 * Usage:
 */
public class SessionManagement {

    public static void setUserSession(User user) {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        session.setAttribute(UserLogin.UID, user.getUid());
        session.setAttribute(UserLogin.USER_NAME, user.getName());
        session.setAttribute(UserLogin.CARD_STATE, user.getConsume().getState());
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

    public static void logout() {
        HttpSession session = getSession();
        if (session == null) {
            return;
        }
        session.invalidate();
    }
}

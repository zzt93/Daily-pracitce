package action;

import com.opensymphony.xwork2.ActionSupport;
import interceptor.SessionManagement;

import javax.servlet.http.HttpSession;

/**
 * Created by zzt on 2/20/16.
 * <p>
 * Usage:
 */
public class Waiter0 extends ActionSupport {

    private int sid;

    public int getSid() {
        return sid;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = SessionManagement.getSession();
        sid = (int) session.getAttribute(InnerLogin.SID);
        return SUCCESS;
    }


    // TODO: 3/1/16 add options, change case in jtable.js
}

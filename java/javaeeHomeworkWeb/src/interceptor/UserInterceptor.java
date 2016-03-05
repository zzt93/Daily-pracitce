package interceptor;

import action.UserLogin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import mis.CardState;

import javax.servlet.http.HttpSession;

/**
 * Created by zzt on 12/17/15.
 * <p>
 * Usage: Check the log in state before request reach the servlet
 */

public class UserInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        if (SessionManagement.checkId(UserLogin.UID)) {
            return ActionSupport.INPUT;
        }
        HttpSession session = SessionManagement.getSession();
        byte state = (byte) session.getAttribute(UserLogin.CARD_STATE);

        if (state == CardState.CANCEL.ordinal()) {
            return ActionSupport.INPUT;
        }

        return invocation.invoke();
    }
}

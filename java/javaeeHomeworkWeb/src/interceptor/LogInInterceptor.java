package interceptor;

import action.UserLogin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zzt on 12/17/15.
 * <p>
 * Usage: Check the log in state before request reach the servlet
 */

public class LogInInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("Interceptor Fired");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(UserLogin.UID) == null) {
            return ActionSupport.INPUT;
        }

        return invocation.invoke();
    }
}

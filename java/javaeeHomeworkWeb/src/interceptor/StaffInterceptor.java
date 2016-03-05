package interceptor;

import action.InnerLogin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import mis.StaffType;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created by zzt on 2/24/16.
 * <p>
 * Usage:
 */
public class StaffInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (SessionManagement.checkId(InnerLogin.SID)) {
            return ActionSupport.INPUT;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        StaffType type = (StaffType) session.getAttribute(InnerLogin.TYPE);
        // check user type with request uri
        String requestURI = request.getRequestURI();
        int start = requestURI.lastIndexOf('/');
        int end = requestURI.lastIndexOf('.');
        String action;
        if (end == -1) {
            action = requestURI.substring(start + 1);
        } else {
            action = requestURI.substring(start + 1, end);
        }
        if (!Objects.equals(action, type.getAction())) {
            return ActionSupport.INPUT;
        }

        return invocation.invoke();
    }

}

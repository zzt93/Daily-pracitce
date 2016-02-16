package interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zzt on 12/16/15.
 * <p>
 * Usage:
 * <p>
 */

public class EncodingInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        /*
         * This method has no effect if it is called after getWriter has been called or after the response has been committed.
         * Note that the character encoding cannot be communicated via HTTP headers if the servlet does not specify a content
         * type; however, it is still used to encode text written via the servlet response's writer
         */
        response.setContentType("text/html;charset=utf-8");
        return invocation.invoke();
    }
}

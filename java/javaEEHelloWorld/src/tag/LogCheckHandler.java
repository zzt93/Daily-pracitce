package tag;

import servlet.SessionManagement;

import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by zzt on 12/23/15.
 * <p>
 * Usage:
 */
public class LogCheckHandler extends BodyTagSupport {

    @Override
    public int doEndTag() throws JspException {
        pageContext.getSession();
        PageContext jspContext = this.pageContext;
        try {
            if (!SessionManagement.checkSession(jspContext.getRequest(), jspContext.getResponse())) {
                return SKIP_PAGE;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return SKIP_PAGE;
        }
        return EVAL_PAGE;
    }
}

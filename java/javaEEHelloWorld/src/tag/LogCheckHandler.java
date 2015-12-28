package tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by zzt on 12/23/15.
 * <p>
 * Usage:
 */
public class LogCheckHandler extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        PageContext jspContext = (PageContext)getJspContext();
        HttpSession session = jspContext.getSession();
        if (session == null) {

        }
    }
}

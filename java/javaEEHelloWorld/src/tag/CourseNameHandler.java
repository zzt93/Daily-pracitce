package tag;

import servlet.CourseServlet;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by zzt on 12/23/15.
 * <p>
 * Usage:
 */
public class CourseNameHandler extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspContext jspContext = getJspContext();
        Integer cid = (Integer) jspContext.findAttribute(CourseServlet.COURSE_ID);
        assert cid != null;

        JspWriter out = getJspContext().getOut();
        out.print(cid + " course name");
    }
}

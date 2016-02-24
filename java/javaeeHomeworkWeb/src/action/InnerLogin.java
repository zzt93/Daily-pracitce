package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zzt on 2/12/16.
 * <p>
 * Usage:
 */
public class InnerLogin extends ActionSupport {


    public static final String SID = "sid";
    private String type;
    private int id;
    private String pw;
    public static final String MANAGER = "manager";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    public static void setStaffSession(int sid) {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession(true);
        session.setAttribute(SID, sid);
    }
}

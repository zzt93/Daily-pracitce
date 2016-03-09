package action;

import com.opensymphony.xwork2.ActionSupport;
import interceptor.SessionManagement;
import mis.StaffType;
import org.apache.struts2.ServletActionContext;
import remote.JNDIFactory;
import service.StaffInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2/12/16.
 * <p>
 * Usage:
 */
public class InnerLogin extends ActionSupport {

    public static final String TYPE = "type";
    private List<String> types = new ArrayList<>(2);

    public static final String SID = "sid";
    private String type;
    private int sid;
    private String pw;

    public InnerLogin() {
        StaffType[] values = StaffType.values();
        for (int i = 0; i < values.length - 1; i++) {
            StaffType staffType = values[i];
            types.add(staffType.getName());
        }
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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
        logOut();
        StaffInfoService staffInfoService =
                (StaffInfoService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded/StaffEJB!service.StaffInfoService");
        StaffType staffType = StaffType.whatType(type);
        assert staffInfoService != null;
        boolean login = staffInfoService.login(sid, pw, staffType.ordinal());
        if (!login) {
            addFieldError("sid", "staff id or password for " + type + " is not match");
            return INPUT;
        }
        SessionManagement.setStaffSession(sid);
        HttpSession session = SessionManagement.getSession();
        session.setAttribute(TYPE, staffType);
        return type;
    }

    public static void setStaffSession(int sid) {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession(true);
        session.setAttribute(SID, sid);
    }

    public String logOut() throws Exception {
        SessionManagement.logout();
        return INPUT;
    }
}

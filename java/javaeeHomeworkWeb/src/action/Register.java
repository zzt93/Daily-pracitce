package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import interceptor.SessionManagement;
import remote.JNDIFactory;
import service.AccountService;

/**
 * Created by zzt on 2/14/16.
 * <p>
 * Usage:
 */
public class Register extends ActionSupport {

    private String name;
    private String pw;
    private String pw2;
    private AccountService accountService = (AccountService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.AccountService");

    @Override
    public String execute() throws Exception {
        SessionManagement.logout();
        String name = getName();

        String pw = getPw();
        if (!pw.equals(pw2)) {
            addFieldError("pw", "password are not same");
            return INPUT;
        }

        User user = accountService.register(name, pw);
        if (user == null) {
            addFieldError("name", "user name is already used.");
            return INPUT;
        } else {
            SessionManagement.setUserSession(user);
        }
        return SUCCESS;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPw2() {
        return pw2;
    }

    public void setPw2(String pw2) {
        this.pw2 = pw2;
    }
}

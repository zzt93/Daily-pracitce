package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by zzt on 2/14/16.
 * <p>
 * Usage:
 */
public class Register extends ActionSupport {

    private String name;
    private String pw;
    private String pw2;

    @Override
    public String execute() throws Exception {
        validate();
        return INPUT;
    }


    public void validate() {
        String name = getName();
        if (false) {
            addFieldError("name", "user name is already used.");
        }

        String pw = getPw();
        if (!pw.equals(pw2)) {
            addFieldError("pw", "pw is not same");
        }

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

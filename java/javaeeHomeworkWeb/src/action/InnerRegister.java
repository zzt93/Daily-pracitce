package action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class InnerRegister extends ActionSupport {

    public static final String ADMIN_PASSWORD = "123";
    // for registration type list
    private List<String> types = new ArrayList<>(2);
    private int sid;

    // request parameter
    private String adminPW;
    private int bid;
    private String pw;
    private String pw2;
    private String type;

    public InnerRegister() {
        types.add("waiter");
        types.add("manager");


    }

    public List<String> getTypes() {
        return Collections.unmodifiableList(types);
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getAdminPW() {
        return adminPW;
    }

    public void setAdminPW(String adminPW) {
        this.adminPW = adminPW;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String execute() throws Exception {
        validate();

        return super.execute();
    }

    public void validate() {
        if (!adminPW.equals(ADMIN_PASSWORD)) {
            addFieldError("adminPW", "wrong admin password");
        }
        String pw = getPw();
        if (!pw.equals(pw2)) {
            addFieldError("pw", "password are not same");
        }
    }
}

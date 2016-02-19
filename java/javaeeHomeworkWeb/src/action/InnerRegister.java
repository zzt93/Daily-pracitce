package action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class InnerRegister extends ActionSupport {

    private ArrayList<String> types = new ArrayList<>(2);

    private String adminPW;
    private int bid;
    private String pw;
    private String pw2;
    private String type;

    public InnerRegister() {
        types.add("waiter");
        types.add("manager");
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
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
        return super.execute();
    }
}

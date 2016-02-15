package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by zzt on 2/12/16.
 * <p>
 * Usage:
 */
public class InnerLogin extends ActionSupport {

    private int id;
    private String pw;

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

    @Override
    public String execute() throws Exception {
        return super.execute();
    }
}

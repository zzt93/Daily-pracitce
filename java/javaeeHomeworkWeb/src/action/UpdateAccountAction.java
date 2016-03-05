package action;

import com.opensymphony.xwork2.ActionSupport;
import interceptor.SessionManagement;
import remote.JNDIFactory;
import service.AccountService;

/**
 * Created by zzt on 3/5/16.
 * <p>
 * Usage:
 */
public class UpdateAccountAction extends ActionSupport {

    private String uname;
    private String location;
    private short age;
    private byte gender;
    private final AccountService accountService;

    public UpdateAccountAction() {
        accountService = (AccountService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_ejb exploded//UserInfoEJB!service.AccountService");

    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String update() throws Exception {
        int uid = SessionManagement.getUid();
        try {
            accountService.fillPublicInfo(uid, uname, location);
            accountService.fillPrivateInfo(uid, age, gender);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
}

package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Account;
import entity.User;
import mis.PayType;
import remote.JNDIFactory;
import service.ConsumeService;

/**
 * Created by zzt on 2/20/16.
 * <p>
 * Usage:
 */
public class OtherWaiter extends ActionSupport {

    // search parameter
    private int userId;

    private User user;
    Account account;

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    private double money;
    private String type;

    public User getUser() {
        return user;
    }

    public Account getAccount() {
        return account;
    }

    public double getMoney() {
        return money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String payMoney() throws Exception {
        PayType payType = PayType.valueOf(type);

        ConsumeService consumeService =
                (ConsumeService) JNDIFactory.getResource("ejb:/javaeeHomeworkEJB_exploded//UserInfoEJB!service.ConsumeService");
        assert consumeService != null;
        consumeService.payMoney(SessionManagement.getUid(), money);
        return SUCCESS;
    }
}

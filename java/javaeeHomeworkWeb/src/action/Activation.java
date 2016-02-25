package action;

import com.opensymphony.xwork2.ActionSupport;
import remote.JNDIFactory;
import service.ConsumeService;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
public class Activation extends ActionSupport {

    private double money;
    private String bankCard;
    private final ConsumeService consumeService;

    public Activation() {
        consumeService = (ConsumeService) JNDIFactory.getResource("");
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    @Override
    public String execute() throws Exception {
        int uid = SessionManagement.getUid();
        consumeService.activateAccount(uid, money, bankCard);
        return SUCCESS;
    }
}

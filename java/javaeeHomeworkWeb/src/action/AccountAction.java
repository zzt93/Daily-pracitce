package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Account;
import entity.Consume;
import entity.User;
import remote.JNDIFactory;
import service.AccountService;

/**
 * Created by zzt on 2/13/16.
 * <p>
 * Usage:
 * Struts 2 Action objects are instantiated for each request, so there are no thread-safety issues.
 */
public class AccountAction extends ActionSupport {

    User user;
    Account account;
    Consume consume;
    //    ArrayList<Reserve> reserves;
    //    ArrayList<Reserve> history;


    public AccountAction() {
        AccountService accountService =
                (AccountService) JNDIFactory.getResource("");
        int uid = SessionManagement.getUid();
        assert accountService != null;
        user = accountService.getUser(uid);
        account = user.getAccount();
        consume = user.getConsume();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }


    public String payMoney() throws Exception {
        return SUCCESS;
    }

}


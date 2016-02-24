package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Account;
import entity.Consume;
import entity.User;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }


    public String payHistoryDelete() throws Exception {
//        result = JTableHelper.OK;
        return SUCCESS;
    }

    public String payHistoryList() throws Exception {
        return SUCCESS;
    }

    public String payMoney() throws Exception {

        return SUCCESS;
    }

}


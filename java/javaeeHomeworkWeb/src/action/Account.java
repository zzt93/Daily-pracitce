package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by zzt on 2/13/16.
 * <p>
 * Usage:
 */
public class Account extends ActionSupport {

    private String name;

    @Override
    public String execute() throws Exception {
        return Account.SUCCESS;
    }

    
}

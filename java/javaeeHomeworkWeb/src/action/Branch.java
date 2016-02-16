package action;

import com.opensymphony.xwork2.ActionSupport;

import java.time.LocalDate;

/**
 * Created by zzt on 2/14/16.
 * <p>
 * Usage:
 */
public class Branch extends ActionSupport {

    private int branchNum;
    private LocalDate now;

    @Override
    public String execute() throws Exception {

        now = LocalDate.now();
        System.out.println(now);
        System.out.println("branch :" + branchNum);
        return SUCCESS;
    }   
    

}

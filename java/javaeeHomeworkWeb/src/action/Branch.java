package action;

import com.opensymphony.xwork2.ActionSupport;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by zzt on 2/14/16.
 * <p>
 * Usage:
 */
public class Branch extends ActionSupport {

    private int branchNum;
    private LocalDate now;
//        private ArrayList<Plan> plans;

    @Override
    public String execute() throws Exception {

        now = LocalDate.now();
        System.out.println(now);
        System.out.println("branch :" + branchNum);
        return SUCCESS;
    }


    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }
}

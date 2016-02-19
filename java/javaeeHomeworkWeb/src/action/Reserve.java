package action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public class Reserve extends ActionSupport {

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    private int branchNum;

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    public String branchReservePay() throws Exception {
        return SUCCESS;
    }

    private String bdate;
    private int did;
    private int num;
    private double price;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String branchReserveAdd() throws Exception {
        return SUCCESS;
    }

    public String branchReserveEdit() throws Exception {
        return SUCCESS;
    }

    public String branchReserveDelete() throws Exception {
        return SUCCESS;
    }
}

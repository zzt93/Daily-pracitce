package vo;

import entity.Branch;
import entity.Reserve;

/**
 * Created by zzt on 3/4/16.
 * <p>
 * Usage:
 */
public class PayVO {


    private final int rid;
    private final Branch branch;
    private final String bdate;
    private final double payment;

    public PayVO(Reserve reserve, double sum) {
        rid = reserve.getRid();
        branch = reserve.getBranch();
        bdate = reserve.getBdate();
        payment = sum;
    }

    public int getRid() {
        return rid;
    }

    public Branch getBranch() {
        return branch;
    }

    public String getBdate() {
        return bdate;
    }

    public double getPayment() {
        return payment;
    }
}

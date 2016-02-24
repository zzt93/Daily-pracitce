package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zzt on 2/17/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "")
public class Plan implements Serializable {

    private int planId;

    private byte state;
    private int bid;
    private String pdate;

    private ArrayList<PlanDetail> details;

    private Branch branch;

    @Id
    @GeneratedValue
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public byte isState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    @OneToMany(mappedBy = "plan")
    public ArrayList<PlanDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<PlanDetail> details) {
        this.details = details;
    }

    @ManyToOne
    @JoinColumn(name = "bid")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
package entity;

import mis.PlanState;

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
@NamedQueries(
        {
                @NamedQuery(query = "select * from Plan p where p.state = 0", name = Plan.NEW_PLAN),
                @NamedQuery(query = "select * from Plan p where p.state = 1 and p.bid = ?1", name = Plan.BRANCH_PLAN)
        }
)
public class Plan implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final String NEW_PLAN = "new plan";
    public static final String BRANCH_PLAN = "branch plan";
    private int planId;

    private byte state;
    private String pdate;

    private ArrayList<PlanDetail> details;

    private Branch branch;

    public Plan() {
    }

    public Plan(Branch branch, String planDate) {
        this.branch = branch;
        this.pdate = planDate;
    }

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
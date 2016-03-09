package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 2/17/16.
 * <p>
 * Usage:
 * <p>
 * <p>
 * The bidirectional associations should always be updated on both sides, therefore the Parent side should contain the
 * addChild and removeChild combo. These methods ensure we always synchronize both sides of the association, to avoid
 * object or relational data corruption issues.
 */
@Entity()
@Table(name = "plan")
@NamedQueries(
        {
                @NamedQuery(query = "select p from Plan p where p.planState = 0", name = Plan.NEW_PLAN),
                @NamedQuery(query = "select count (p) from Plan p where p.planState = 0", name = Plan.COUNT_NEW_PLAN),
                @NamedQuery(query = "select p from Plan p where p.planState = 1 and p.branch.bid = ?1 and p.pdate >= ?2 order by p.pdate", name = Plan.BRANCH_PLAN),
                @NamedQuery(query = "select p from Plan p where p.staff.sid = ?1 and p.planState <> 1", name = Plan.STAFF_PLAN),
                @NamedQuery(query = "select p from Plan p where p.branch.bid = ?1 and p.pdate = ?2", name = Plan.SAME_PLAN),
        }
)
public class Plan implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final String NEW_PLAN = "new plan";
    public static final String BRANCH_PLAN = "branch plan";
    public static final String STAFF_PLAN = "staff submitted plan";
    public static final String SAME_PLAN = "same plan";
    public static final java.lang.String COUNT_NEW_PLAN = "count new plan";
    private int planId;

    private byte planState;
    private String pdate;

    //    private Set<PlanDetail> details = new HashSet<>();

    private Branch branch;

    private Staff staff;

    public Plan() {
    }

    public Plan(Staff staff, Branch branch, String planDate) {
        this.staff = staff;
        this.branch = branch;
        this.pdate = planDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Column(name = "state")
    public byte getPlanState() {
        return planState;
    }

    public void setPlanState(byte planState) {
        this.planState = planState;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    //    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    //    public Set<PlanDetail> getDetails() {
    //        return details;
    //    }

    //    public void setDetails(Set<PlanDetail> details) {
    //        this.details = details;
    //        for (PlanDetail detail : details) {
    //            addDetail(detail);
    //        }
    //    }

    //    public void addDetail(PlanDetail detail) {
    //        details.add(detail);
    //        detail.setPlan(this);
    //    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "bid")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "sid")
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        return planId == plan.planId;

    }

    @Override
    public int hashCode() {
        return planId;
    }

    //    public void initLazy() {
    //        Branch branch = getBranch();
    //        branch.initLazy();
    //        Staff staff = getStaff();
    //        staff.initLazy();
    //        for (PlanDetail planDetail : getDetails()) {
    //            planDetail.initLazy();
    //        }
    //    }
}
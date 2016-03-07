package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 2/16/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "pdetail")
@NamedQueries(
        {
                @NamedQuery(name = PlanDetail.A_PLAN_DETAIL,
                        query = "select pd from PlanDetail pd where pd.plan.planId = ?1"),

                @NamedQuery(name = PlanDetail.DETAIL_BY_BID_DATE_DID,
                        query = "select pd from PlanDetail pd where pd.plan.branch.bid = ?1 " +
                                "and pd.plan.pdate = ?2 and pd.dessert.did = ?3"),

        }
)
public class PlanDetail implements Serializable {
    public static final long serialVersionUID = 42L;
    public static final String A_PLAN_DETAIL = "a plan's details";
    public static final java.lang.String DETAIL_BY_BID_DATE_DID = "detail by bid, date, did";

    private int pdId;
    private int num;
    private double price;

    private Plan plan;
    private Dessert dessert;

    public PlanDetail() {
    }

    public PlanDetail(Plan plan, Dessert dessert, double price, int num) {
        this.plan = plan;
        this.dessert = dessert;
        this.price = price;
        this.num = num;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPdId() {
        return pdId;
    }

    public void setPdId(int pdId) {
        this.pdId = pdId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "planId")
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "did")
    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanDetail that = (PlanDetail) o;

        return pdId == that.pdId;

    }

    @Override
    public int hashCode() {
        return pdId;
    }

    public void initLazy() {
        //        getDessert().initLazy();
    }
}
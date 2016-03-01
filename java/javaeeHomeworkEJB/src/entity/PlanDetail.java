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
public class PlanDetail implements Serializable {
    public static final long serialVersionUID = 42L;

    private int pdId;
    private int num;

    private Plan plan;
    private Dessert dessert;

    public PlanDetail() {
    }

    public PlanDetail(Plan plan, Dessert dessert, int num) {
        this.num = num;
        this.dessert = dessert;
        this.plan = plan;
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
}
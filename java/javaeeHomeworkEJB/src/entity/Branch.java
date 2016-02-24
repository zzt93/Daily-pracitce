package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zzt on 2/22/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "branch")
@NamedQuery(query = "select * from Branch", name = Branch.ALL_BRANCH)
public class Branch implements Serializable {

    public static final String ALL_BRANCH = "all branch";
    private int bid;
    private String addr;

    private ArrayList<Plan> plans;
    private ArrayList<Reserve> reserves;

    public Branch() {
    }

    public Branch(String addr) {
        this.addr = addr;
    }

    @Id
    @GeneratedValue
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @OneToMany(mappedBy = "branch")
    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    @OneToMany(mappedBy = "branch")
    public ArrayList<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(ArrayList<Reserve> reserves) {
        this.reserves = reserves;
    }
}
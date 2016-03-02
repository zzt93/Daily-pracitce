package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzt on 2/22/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "branch")
@NamedQuery(query = "select b from Branch b", name = Branch.ALL_BRANCH)
public class Branch implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final String ALL_BRANCH = "all branch";
    private int bid;
    private String addr;

//    private Set<Plan> plans = new HashSet<>();
//    private Set<Reserve> reserves = new HashSet<>();

    public Branch() {
    }

    public Branch(String addr) {
        this.addr = addr;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    public Set<Plan> getPlans() {
//        return plans;
//    }

//    public void setPlans(Set<Plan> plans) {
//        this.plans = plans;
//    }

//    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    public Set<Reserve> getReserves() {
//        return reserves;
//    }

//    public void setReserves(Set<Reserve> reserves) {
//        this.reserves = reserves;
//    }
//
//    public void initLazy() {
//        getPlans().size();
//        getReserves().size();
//    }
}
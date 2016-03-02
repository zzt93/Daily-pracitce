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
@Table(name = "staff")
@NamedQuery(name = Staff.MAX_ID, query = "select max(s.sid) from Staff s")
public class Staff implements Serializable{
    public static final long serialVersionUID = 42L;
    public static final String MAX_ID = "max id";

    private int sid;
    private String pw;
    private int type;

    private Branch branch;

//    private Set<Plan> plans = new HashSet<>();

    public Staff() {
    }

    public Staff(Branch branch, String pw, int type) {
        this.branch = branch;
        this.pw = pw;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "bid")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

//    @OneToMany(mappedBy = "staff")
//    public Set<Plan> getPlans() {
//        return plans;
//    }

//    public void setPlans(Set<Plan> plans) {
//        this.plans = plans;
//    }

//    public void initLazy() {
//        getPlans().size();
//    }
}
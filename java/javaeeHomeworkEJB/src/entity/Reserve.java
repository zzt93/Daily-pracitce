package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zzt on 2/16/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "reserve")
public class Reserve implements Serializable {


    private int rid;

    private String bdate;
    private boolean state;

    private User user;
    private Branch branch;

    @Id
    @GeneratedValue
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "bid")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    private ArrayList<ReserveDetail> details;

    @OneToMany(mappedBy = "reserve")
    public ArrayList<ReserveDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<ReserveDetail> details) {
        this.details = details;
    }
}
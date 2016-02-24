package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 2/22/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "staff")
public class Staff implements Serializable{
    public static final long serialVersionUID = 42L;

    private int sid;
    private String pw;

    private Branch branch;

    public Staff() {
    }

    public Staff(Branch branch, String pw) {
        this.branch = branch;
        this.pw = pw;
    }

    @Id
    @GeneratedValue
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

    @ManyToOne
    @JoinColumn(name = "bid")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
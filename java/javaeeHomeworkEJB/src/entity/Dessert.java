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
@Table(name = "dessert")
public class Dessert implements Serializable {
    public static final long serialVersionUID = 42L;

    private int did;
    private String name;

    @Id
    @GeneratedValue
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ArrayList<PlanDetail> details;

    @OneToMany(mappedBy = "dessert")
    public ArrayList<PlanDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<PlanDetail> details) {
        this.details = details;
    }

    private ArrayList<ReserveDetail> reserveDetails;

    @OneToMany(mappedBy = "dessert")
    public ArrayList<ReserveDetail> getReserveDetails() {
        return reserveDetails;
    }

    public void setReserveDetails(ArrayList<ReserveDetail> reserveDetails) {
        this.reserveDetails = reserveDetails;
    }
}
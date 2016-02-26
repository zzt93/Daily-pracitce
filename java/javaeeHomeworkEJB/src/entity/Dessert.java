package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    private Set<PlanDetail> details;

    /**
     * Hibernate requires that persistent collection-valued fields be declared as an interface type
     * @return
     */
    @OneToMany(mappedBy = "dessert")
    public Set<PlanDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<PlanDetail> details) {
        this.details = details;
    }

    private Set<ReserveDetail> reserveDetails;

    @OneToMany(mappedBy = "dessert")
    public Set<ReserveDetail> getReserveDetails() {
        return reserveDetails;
    }

    public void setReserveDetails(Set<ReserveDetail> reserveDetails) {
        this.reserveDetails = reserveDetails;
    }
}
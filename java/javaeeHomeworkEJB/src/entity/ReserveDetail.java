package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "rdetail")
public class ReserveDetail implements Serializable {
    public static final long serialVersionUID = 42L;

    private int rdid;

    private int num;
    private double price;

    private Reserve reserve;
    private Dessert dessert;

    @Id
    @GeneratedValue
    public int getRdid() {
        return rdid;
    }

    public void setRdid(int rdid) {
        this.rdid = rdid;
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

    @ManyToOne
    @JoinColumn(name = "rid")
    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    @ManyToOne
    @JoinColumn(name = "did")
    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }
}
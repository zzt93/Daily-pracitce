package entity;

import mis.Default;
import mis.Rank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "rdetail")
@NamedQueries(
        {
                @NamedQuery(name = ReserveDetail.A_RESERVE_DETAIL, query = "select rd from ReserveDetail rd where rd.reserve.rid = ?1"),

                @NamedQuery(name = ReserveDetail.SUM_AMOUNT_BY_DESSERT,
                        query = "select sum(r.num) from ReserveDetail r where r.dessert.did = ?1"),

        }
)
public class ReserveDetail implements Serializable {
    public static final long serialVersionUID = 42L;
    public static final java.lang.String A_RESERVE_DETAIL = "a reserve's details";
    public static final String SUM_AMOUNT_BY_DESSERT = "sale amount sum ByDessert";

    private int rdid;

    private int num;
    private double price;

    private Reserve reserve;
    private Dessert dessert;

    public ReserveDetail() {
    }

    public ReserveDetail(int num, double price, Reserve reserve, Dessert dessert) {
        this.num = num;
        this.price = price;
        this.reserve = reserve;
        this.dessert = dessert;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "rid")
    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    @ManyToOne(optional = false)
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

        ReserveDetail that = (ReserveDetail) o;

        return rdid == that.rdid;

    }

    @Override
    public int hashCode() {
        return rdid;
    }

    public double toPayReservation() {
        return num * price * Default.RESERVE_RATIO;
    }

    public double toPayRest(Rank rank) {
        return num * price * rank.getRatio();
    }
}
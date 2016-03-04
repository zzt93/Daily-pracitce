package tmpEntity;

import java.io.Serializable;

/**
 * Created by zzt on 3/4/16.
 * <p>
 * Usage:
 */
public class RDBranchVO implements Serializable {
    public static final long serialVersionUID = 42L;


    private final String dessertName;
    private int did;
    private int num;
    private double price;
    private final int tmpId;
    private ReserveBranchVO reserve;

    public RDBranchVO(int tmpId, int did, String dessertName, int num, double price) {
        this.tmpId = tmpId;
        this.did = did;
        this.num = num;
        this.price = price;
        this.dessertName = dessertName;
    }

    public int getTmpId() {
        return tmpId;
    }

    public int getDid() {
        return did;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDessertName() {
        return dessertName;
    }

    public double getPrice() {
        return price;
    }

    public ReserveBranchVO getReserve() {
        return reserve;
    }

    public void setReserve(ReserveBranchVO reserve) {
        this.reserve = reserve;
    }
}

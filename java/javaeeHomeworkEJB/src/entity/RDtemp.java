package entity;

/**
 * Created by zzt on 2/25/16.
 * <p>
 * Usage:
 */
public class RDtemp {


    private int num;
    private double price;

    private int rid;
    private int did;

    public RDtemp(int num, double price, int rid, int did) {
        this.num = num;
        this.price = price;
        this.rid = rid;
        this.did = did;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public int getRid() {
        return rid;
    }

    public int getDid() {
        return did;
    }
}

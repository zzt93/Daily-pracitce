package entity;

import mis.Default;
import mis.Rank;
import scheduler.BalanceChecker;
import scheduler.Starter;
import service.exception.BalanceNotEnoughException;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "consume")
@NamedQueries(
        {
                @NamedQuery(name = Consume.OWNING_MONEY_USER, query = "select c from Consume c where c.balance < 0"),
                @NamedQuery(name = Consume.COUNT_OWNING_MONEY_USER, query = "select count (c) from Consume c where c.balance < 0"),
                @NamedQuery(name = Consume.ALL_CONSUME, query = "select c from Consume c"),
                @NamedQuery(name = Consume.COUNT_ALL_CONSUME, query = "select count (c) from Consume c"),

        }
)
public class Consume implements Serializable {
    public static final long serialVersionUID = 42L;
    public static final String OWNING_MONEY_USER = "owning money user";
    public static final String COUNT_OWNING_MONEY_USER = "count user";
    public static final String ALL_CONSUME = "all consume";
    public static final String COUNT_ALL_CONSUME = "count all consume";

    private int uid;

    private byte state;
    private String nDate;
    private double balance;
    private byte rank;
    private int credit;

    private User user;

    public Consume() {
    }

    public Consume(User user) {
        this.user = user;
        user.setConsume(this);
        /*
        @see {Account}
                uid = user.getUid();
         */
    }

    @MapsId
    @OneToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getnDate() {
        return nDate;
    }

    public void setnDate(String nDate) {
        this.nDate = nDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public byte getRank() {
        return rank;
    }

    public void setRank(byte rank) {
        this.rank = rank;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void payReservation(double price) throws BalanceNotEnoughException {
        pay(price * Default.RESERVE_RATIO);
    }

    public void pay(double price) throws BalanceNotEnoughException {
        if (getBalance() >= price) {
            balance -= price;
            credit += price;
        } else if (getBalance() + Rank.values()[rank].getCredit() >= price) {
            credit += price;
            balance -= price;
            nDate = nowPlusOneYear();
            // add user to check
            synchronized (BalanceChecker.class) {
                if (BalanceChecker.noUser()) {
                    new Starter().startChecking(user);
                } else {
                    BalanceChecker.addUser(user);
                }
            }
        } else {
            throw new BalanceNotEnoughException("no enough money to pay this order");
        }
    }

    private String nowPlusOneYear() {
        String now = LocalDate.now().toString();
        return now.substring(0, 3) + (char) (now.charAt(3) + 1) + now.substring(4);
    }
}
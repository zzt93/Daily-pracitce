package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 2/15/16.
 * <p>
 * Usage:
 */
@Entity
@Table(name = "account")
@NamedQueries(
        {
                @NamedQuery(query = "select a from Account a where a.uid = ?1", name = Account.FIND_ACCOUNT_BY_ID),
                @NamedQuery(query = "select a.gender, count (a) from Account a where a.age >= ?1 and a.age < ?2 group by a.gender", name = Account.ACCOUNT_COUNT_GENDER_AGE),
        }

)
public class Account implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final String FIND_ACCOUNT_BY_ID = "find account by id";
    public static final String ACCOUNT_COUNT_GENDER_AGE = "accountCountByGenderAndAge";
    private int uid;

    private String bankCard;
    private String addr;
    private short age;
    private byte gender;

    private User user;

    public Account() {
    }

    public Account(User user) {
        this.user = user;
        user.setAccount(this);
        /*
         can't assign it, why?
        cause error: null value was assigned to a property of primitive type setter uid
         */
        //        uid = user.getUid();
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

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }
}

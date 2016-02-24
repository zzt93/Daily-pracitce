package entity;

import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by zzt on 2/15/16.
 * <p>
 * Usage:
 */
@Entity
@Table(name = "account")
@NamedQuery(query = "select * from Account a where a.uid = ?1", name = Account.FIND_ACCOUNT_BY_ID)
public class Account implements Serializable {

    public static final String FIND_ACCOUNT_BY_ID = "find account by id";
    private int uid;

    private String bankCard;
    private String addr;
    private short age;
    private byte gender;

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

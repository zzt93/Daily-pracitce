package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Entity()
@Table(name = "msg")
@NamedQuery(name = Message.ALL_MESSAGE, query = "select * from Message m where m.uid = ?1")
public class Message implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final String ALL_MESSAGE = "all message";
    private int mid;
    private String msg;

    private User user;

    public Message() {
    }

    public Message(String msg, User user) {
        this.msg = msg;
        this.user = user;
    }

    @Id
    @GeneratedValue
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
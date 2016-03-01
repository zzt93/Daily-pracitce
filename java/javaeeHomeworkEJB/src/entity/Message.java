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
@NamedQueries(
        {
                @NamedQuery(name = Message.USER_MESSAGE, query = "select m from Message m where m.user.uid = ?1"),
                @NamedQuery(name = Message.COUNT_USER_MESSAGE, query = "select count(m) from Message m where m.user.uid = ?1")
        }
)
public class Message implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final String USER_MESSAGE = "user message";
    public static final String COUNT_USER_MESSAGE = "count user message";
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
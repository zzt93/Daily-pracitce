package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by zzt on 2/11/16.
 * <p>
 * Usage:
 * Bidirectional Relationships:
 * Each entity has a relationship field or property that refers to the other entity.
 * <p>
 * For one-to-one bidirectional relationships, the owning side corresponds to the side that contains the corresponding
 * foreign key. In general, parent class is inverse side, child class is owning side.
 * <p></p>
 * In this case, `alibaba.User` is inverse side
 * <p>
 * The inverse side of a bidirectional relationship must refer to its owning side
 * by using the mappedBy element of the @OneToOne, @OneToMany, or @ManyToMany annotation
 */
@Entity
@Table(name = "user")
@NamedQuery(query = "SELECT u FROM User u where u.name = ?1", name = User.FIND_USER_BY_NAME)
public class User implements Serializable {

    public static final long serialVersionUID = 42L;
    public static final String FIND_USER_BY_NAME = "find user by name";

    private int uid;
    private String name;
    private String pw;

    public User() {
    }

    public User(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    private Account account;
    private Consume consume;

    /*
    add `optional = false`, make `user` reference `consume` and `account` use `uid`
    i.e. add foreign key to user table
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    public Consume getConsume() {
        return consume;
    }

    public void setConsume(Consume consume) {
        this.consume = consume;
    }

//    private Set<Reserve> reserves;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    public Set<Reserve> getReserves() {
//        return reserves;
//    }

//    public void setReserves(Set<Reserve> reserves) {
//        this.reserves = reserves;
//    }

//    private Set<Message> messages;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    public Set<Message> getMessages() {
//        return messages;
//    }

//    public void setMessages(Set<Message> messages) {
//        this.messages = messages;
//    }
}

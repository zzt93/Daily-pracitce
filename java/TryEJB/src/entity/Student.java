package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzt on 1/4/16.
 * <p>
 * Usage: Objects of this class have to be transmitted to client, so it has to be serializable
 * <p>
 * - update data in the database automatically when you update the data in the memory - make all instance refer to
 * latest data after update
 * <p>
 * The entity’s data will be removed from the data store when the transaction is completed, or as a result of the flush
 * operation.
 *
 *
 * This class is the inverse side of many-to-many side
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    public static final long serialVersionUID = 42L;



    public Student() {
    }

    private int sid;
    private String name;
    private String password;

    private Set<Course> courses = new HashSet<>();


    public Student(String name, String password) {
        setName(name);
        setPassword(password);
    }

    @Id
    @GeneratedValue
    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    The inverse side of a bidirectional relationship must refer to its owning side
    by using the mappedBy element of the @OneToOne, @OneToMany, or @ManyToMany annotation.

    mappedBy is to specify a field in entity.Course which is actually object of this class(or set of this class)
    maybe the process is: create an instance of entity.Course and map to entity.Student
     */
    @ManyToMany(mappedBy = "students")
    /**
     * Join score using sid specified by <a link="Course#getStudent"></a>
     * @return the courses a specific student entity choose
     * @see Course#getStudents()
     */
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}

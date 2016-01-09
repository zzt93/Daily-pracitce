package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzt on 1/6/16.
 * <p>
 * Usage: This class is the owning side of many-to-many side because it is not use 'mappedBy'
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    public static final long serialVersionUID = 42L;

    int cid;
    String cname;

    private Set<Student> students = new HashSet<>();

    public Course() {
    }

    @Id
    @GeneratedValue
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    /*
    Used in the mapping of associations. It is specified on the owning side of an association.
    table `course` join table `score` using a column cid which is named courseId, so that to
    get the students choose the some specific course(which is a course entity)
    i.e. specify the courseId as foreign key of score to join with this table's primary key
    i.e. specify the studentId as the foreign key of score to join with this table's primary key

    name: the foreign key name in the score
    referencedColumnName: the name in the course/student
     */
    @JoinTable(
            name = "score",
            joinColumns =
            @JoinColumn(name = "cid", referencedColumnName = "cid"),
            inverseJoinColumns =
            @JoinColumn(name = "sid", referencedColumnName = "sid")
    )
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}

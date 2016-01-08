package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zzt on 1/8/16.
 * <p>
 * Usage:
 */
@Entity
@Table(name = "score")
public class Score implements Serializable{


    int subid;
    boolean submit;

    private Student student;
    private Course course;

    public Score() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSubid() {
        return subid;
    }

    public boolean isSubmit() {
        return submit;
    }

    /*
    Defines a single-valued association to another entity class that has many-to-one multiplicity
    i.e. student is one, score is many
     */
    @ManyToOne(cascade = CascadeType.ALL)
    /*
    JoinColumn is somewhat like specify the foreign key of this table
     */
    @JoinColumn(name = "studentId")
    public Student getStudent() {
        return student;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId")
    public Course getCourse() {
        return course;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }
}

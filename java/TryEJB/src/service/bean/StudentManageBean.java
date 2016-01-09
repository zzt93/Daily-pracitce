package service.bean;

import entity.Score;
import entity.Student;
import service.StudentManageService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by zzt on 1/6/16.
 * <p>
 * Usage:
 */
@Stateless(name = "StudentManageEJB")
public class StudentManageBean implements StudentManageService{

    /**
     * container managed entity manager: entity are in a single persistence
     * context and within a JTA transaction
     */
    @PersistenceContext
    EntityManager entityManager;

    public StudentManageBean() {
    }

    @Override
    public boolean regStudent(String name, String password) {
        Student student = new Student(name, password);
        entityManager.persist(student);
        return true;
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        Query query = entityManager.createQuery("select s from student s");
        return (ArrayList<Student>) query.getResultList();
    }

    @Override
    public boolean loginStudent(int sid, String pw) {
        Student student = entityManager.find(Student.class, sid);
        return student != null && student.getPassword().equals(pw);
    }
}

package service.bean;

import entity.Course;
import service.CourseService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 1/6/16.
 * <p>
 * Usage:
 */
@Stateless(name = "CourseEJB")
public class CourseBean implements CourseService{

    @PersistenceContext
    EntityManager entityManager;

    public CourseBean() {
    }

    @Override
    public Course getCourse(int cid) {
        return entityManager.find(Course.class, cid);
    }

    @Override
    public ArrayList<Course> allCourses() {
        Query query = entityManager.createQuery("select c from course c");
        return (ArrayList<Course>) query.getResultList();
    }


}

package service;

import entity.Course;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by zzt on 1/6/16.
 * <p>
 * Usage:
 */
@Remote
public interface CourseService {

    Course getCourse(int cid);
    ArrayList<Course> allCourses();


}

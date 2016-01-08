package service;

import entity.Student;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by zzt on 1/5/16.
 * <p>
 * Usage:
 */
@Remote
public interface StudentManageService {

    boolean regStudent(String name, String password);
    ArrayList<Student> getAllStudents();
    boolean loginStudent(int sid, String pw);
}

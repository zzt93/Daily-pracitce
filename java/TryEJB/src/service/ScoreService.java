package service;

import entity.Course;
import entity.Score;

import javax.ejb.Remote;
import java.util.ArrayList;

/**
 * Created by zzt on 1/8/16.
 * <p>
 * Usage:
 */
@Remote
public interface ScoreService {
    ArrayList<Score> studentCourses(int sid);
}

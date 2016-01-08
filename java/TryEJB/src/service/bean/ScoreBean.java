package service.bean;

import entity.Course;
import entity.Score;
import service.ScoreService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 1/8/16.
 * <p>
 * Usage:
 */
@Stateless(name = "ScoreEJB")
public class ScoreBean implements ScoreService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<Score> studentCourses(int sid) {
        Query query = entityManager.createQuery("from score c where c.sid = " + sid);
        List list =query.getResultList();
        entityManager.clear();//在处理大量实体的时候，如果不把已经处理过的实体从EntityManager中分离出来，将会消耗大量的内存；此方法分离内存中受管理的实体Bean，让VM进行垃圾回收
        return (ArrayList<Score>) list;
    }
}

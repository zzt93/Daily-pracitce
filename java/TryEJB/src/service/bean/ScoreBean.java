package service.bean;

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
        /**
         * Though it looks like sql, your executing JPA Query Language;
         * so you're selecting from Entities, not from tables.
         */
        Query query = entityManager.createNamedQuery(Score.FIND_STUDENT_S_SCORES);
        query.setParameter(1, sid);
//        Query query = entityManager.createQuery("select s from Score s where s.student.sid = 1");
        List list = query.getResultList();
        /*
        Clear the persistence context, causing all managed entities to become detached. -- make
        some entities can be garbage collected
        Changes made to entities that have not been flushed to the database will not be persisted.
         */
        entityManager.clear();
        return (ArrayList<Score>) list;
    }
}

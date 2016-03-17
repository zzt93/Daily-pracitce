package dao;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zzt on 3/13/16.
 * <p>
 * Usage:
 */
@Repository
public class UserDao {

    @Autowired
    protected SessionFactory sessionFactory;

    public Session getNewSession() {
        return sessionFactory.openSession();
    }

    public void save(User user) {
        try {
            Session session = getNewSession();
            session.save(user);
            session.flush();
            session.clear();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User find(String name) {
        Criteria criteria = getNewSession().createCriteria(User.class);
        List<User> users = criteria.list();
        //查询符合条件的user
        criteria.add(Expression.eq("userName", name));
        users = criteria.list();
        return users.get(0);
    }
}

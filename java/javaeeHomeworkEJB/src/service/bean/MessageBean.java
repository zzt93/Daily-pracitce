package service.bean;

import entity.Message;
import entity.User;
import service.MessageService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "MessageEJB")
public class MessageBean implements MessageService{

    @PersistenceContext
    EntityManager em;

    public MessageBean() {
    }

    @Override
    public void addMsg(String msg, int toUser) {
        User user = em.find(User.class, toUser);
        em.persist(new Message(msg, user));
    }

    @Override
    public void deleteMsg(int mid) {
        em.remove(em.find(Message.class, mid));
    }

    @Override
    public ArrayList<Message> userMsg(int uid, int startIndex, int pageSize) {
        return (ArrayList<Message>) em.createNamedQuery(Message.USER_MESSAGE, Message.class)
                .setParameter(1, uid)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    /**
     *
     * @param uid user id
     * @return count result in Long
     */
    @Override
    public long countUserMsg(int uid) {
        return (Long) em.createNamedQuery(Message.COUNT_USER_MESSAGE)
                .setParameter(1, uid)
                .getSingleResult();
    }
}

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
    public ArrayList<Message> allMsg(int uid) {
        return (ArrayList<Message>) em.createNamedQuery(Message.ALL_MESSAGE, Message.class)
                .setParameter(1, uid)
                .getResultList();
    }
}

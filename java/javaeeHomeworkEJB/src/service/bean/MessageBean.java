package service.bean;

import entity.Message;
import service.MessageService;

import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 * Created by zzt on 2/23/16.
 * <p>
 * Usage:
 */
@Stateless(name = "MessageEJB")
public class MessageBean implements MessageService{
    public MessageBean() {
    }

    @Override
    public void addMsg(String addr) {

    }

    @Override
    public void deleteMsg(int bid) {

    }

    @Override
    public ArrayList<Message> allMsg() {
        return null;
    }
}

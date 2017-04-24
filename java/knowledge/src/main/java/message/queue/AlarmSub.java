package message.queue;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by zzt on 4/20/17.
 * <p>
 * <h3></h3>
 */
public class AlarmSub {

    public String await() throws NamingException, JMSException {
        Context context = new InitialContext();
        TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("TopicConnectionFactory");
        Topic alarms = (Topic) context.lookup("Alarms");
        TopicConnection topicConnection = factory.createTopicConnection();
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = topicSession.createSubscriber(alarms);
        Message receive = subscriber.receive();
        return receive.toString();
    }
}

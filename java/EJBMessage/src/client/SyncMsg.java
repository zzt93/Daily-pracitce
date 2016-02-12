package client;

import javax.jms.*;
import javax.naming.NamingException;

/**
 * Created by zzt on 1/5/16.
 * <p>
 * Usage:
 * Synchronized message receiver
 * get the right context and queue to waiting for a message
 */
public class SyncMsg {
    public static void main(String[] args) throws NamingException, JMSException {
        Queue queue;
        ConnectionFactory factory = (ConnectionFactory) JNDIFactory.getResource("java:/ConnectionFactory");
        queue = (Queue) JNDIFactory.getResource("java:/jboss/exported/jms/queue/testQueue");

        assert factory != null;
        try (JMSContext context = factory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(queue);
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("Reading message: " + message.getText());
            message = (TextMessage) consumer.receive();
            System.out.println("Reading message: " + message.getText());
        }
    }
}

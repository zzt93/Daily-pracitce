package client;

import javax.jms.*;
import javax.naming.NamingException;

/**
 * Created by zzt on 1/5/16.
 * <p>
 * Usage:
 * Asynchronous message listener:
 * get the right server context and queue to monitor a queue's message change
 */
public class AsyncMsg implements MessageListener {
    public AsyncMsg() throws JMSException, NamingException {
        QueueConnectionFactory factory = (QueueConnectionFactory) JNDIFactory.getResource("ConnectionFactory");
        Queue queue = (Queue) JNDIFactory.getResource("queue/MyTestQueue");
        JMSContext context = factory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        JMSConsumer consumer = context.createConsumer(queue);
        consumer.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage);
        }
    }
}

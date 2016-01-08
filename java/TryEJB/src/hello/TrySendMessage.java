package hello;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zzt on 1/5/16.
 * <p>
 * Usage:
 * JMS producer: it will produce one copy and put into
 * the right context and queue, waiting for the receiving
 * of message
 *
 *
 */
@WebServlet(urlPatterns = "/send")
public class TrySendMessage extends HttpServlet {
    @Resource(mappedName = "java:/ConnectionFactory")
    private QueueConnectionFactory factory;
    @Resource(mappedName = "java:/jboss/exported/jms/queue/testQueue")
    private Queue queue;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (JMSContext context = factory.createContext();) {
            TextMessage message = context.createTextMessage();
            message.setText(req.getParameter("message1"));
            context.createProducer().send(queue, message);
            System.out.println("Sending message: " + message.getText());
            message.setText(req.getParameter("message2"));
            context.createProducer().send(queue, message);
            System.out.println("Sending message: " + message.getText());}
        catch (JMSException e) {
            e.printStackTrace();
        }
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");

    }

}

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzt
 */
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("username@gmail.com"));

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("targetemail@example.com"));
			msg.setSubject("test");
			msg.setText("test");
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

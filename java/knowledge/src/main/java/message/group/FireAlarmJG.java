package message.group;


import org.jgroups.JChannel;
import org.jgroups.Message;

/**
 * Created by zzt on 4/17/17.
 * <p>
 * <h3></h3>
 */
public class FireAlarmJG {
    public void raise() {
        try {
            JChannel channel = new JChannel();
            channel.connect("AlarmGroup");
            Message msg = new Message(null, null, "Fire!");
            channel.send(msg);
        } catch (Exception e) {
        }
    }
}

package message.group;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

/**
 * Created by zzt on 4/18/17.
 * <p>
 * <h3></h3>
 */
public class FireAlarmConsumerJG {
    public void await() {
        try {
            JChannel channel = new JChannel();
            channel.connect("AlarmGroup");
            channel.setReceiver(new ReceiverAdapter() {
                @Override
                public void receive(Message msg) {
                    System.out.println(msg);
                }
            });
        } catch (Exception e) {
            return;
        }
    }
}

package message.sharedMem;

import net.jini.core.entry.Entry;
import net.jini.space.JavaSpace;

/**
 * Created by zzt on 4/25/17.
 * <p>
 * <h3></h3>
 */
class AlarmTupleJS implements Entry {
    public String alarmType;
    public AlarmTupleJS() {
    }
    public AlarmTupleJS(String alarmType) {
        this.alarmType = alarmType;
    }
}

public class FireAlarmJS {
    public void raise() {
        try {
//            JavaSpace space = SpaceAccessor.findSpace("AlarmSpace");
//            AlarmTupleJS tuple = new AlarmTupleJS("Fire!");
//            space.write(tuple, null, 60 * 60 * 1000);
        } catch (Exception e) {
        }
    }
}
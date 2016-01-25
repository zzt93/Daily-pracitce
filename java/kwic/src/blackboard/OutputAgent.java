package blackboard;

import blackboard.dataCenter.DataCenter;
import blackboard.dataCenter.DataStage;
import blackboard.dataCenter.NotifyInterface;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class OutputAgent implements NotifyInterface{
    public OutputAgent() throws FileNotFoundException {
        DataCenter test = DataCenter.getInstance();
        test.register(DataStage.SORTED, this);
    }

    @Override
    public boolean notice() {
        DataCenter instance = null;
        try {
            instance = DataCenter.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            instance.popData(DataStage.SORTED).getStrings().forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}

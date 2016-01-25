package blackboard;

import blackboard.dataCenter.AimData;
import blackboard.dataCenter.DataCenter;
import blackboard.dataCenter.DataStage;
import blackboard.dataCenter.NotifyInterface;
import pipeFilter.Shift;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class ShiftAgent implements NotifyInterface{

    public ShiftAgent() throws FileNotFoundException {
        DataCenter test = DataCenter.getInstance();
        test.register(DataStage.NEW, this);
    }

    @Override
    public boolean notice() {
        DataCenter dataCenter = null;
        try {
            dataCenter = DataCenter.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            for (String s : dataCenter.popData(DataStage.NEW).getStrings()) {
                dataCenter.pushData(DataStage.SHIFTED, new AimData(Shift.shift(s)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}

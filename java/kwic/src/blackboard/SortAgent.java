package blackboard;

import blackboard.dataCenter.AimData;
import blackboard.dataCenter.DataCenter;
import blackboard.dataCenter.DataStage;
import blackboard.dataCenter.NotifyInterface;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class SortAgent implements NotifyInterface {

    public SortAgent() throws FileNotFoundException {
        DataCenter test = DataCenter.getInstance();
        test.register(DataStage.READY_FOR_SORT, this);
    }

    @Override
    public boolean notice() {
        DataCenter test;
        try {
            test = DataCenter.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        LinkedBlockingQueue<AimData> aimDatas = test.getDatas().get(DataStage.SHIFTED);
        ArrayList<String> strings = new ArrayList<>();
        for (AimData aimData : aimDatas) {
            strings.addAll(aimData.getStrings());
        }
        Collections.sort(strings);
        try {
            test.pushData(DataStage.SORTED, new AimData(strings));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

}

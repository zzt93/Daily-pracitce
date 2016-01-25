package blackboard;

import blackboard.dataCenter.DataCenter;
import blackboard.dataCenter.DataStage;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class BoardTester {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        DataCenter test = DataCenter.getInstance();
        new ShiftAgent();
        new SortAgent();
        new OutputAgent();
        test.start();
    }
}

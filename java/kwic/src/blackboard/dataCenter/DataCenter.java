package blackboard.dataCenter;

import utility.MyIn;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class DataCenter implements MonitorInterface {
    public static final String TEST = "test";
    private static DataCenter ourInstance;
    private final MyIn in;

    private HashMap<DataStage, ArrayList<NotifyInterface>> routers
            = new HashMap<>();
    private ConcurrentHashMap<DataStage, LinkedBlockingQueue<AimData>> datas
            = new ConcurrentHashMap<>();

    public static DataCenter getInstance() throws FileNotFoundException {
        if (ourInstance == null) {
            ourInstance = new DataCenter(TEST);
        }
        return ourInstance;
    }

    private DataCenter(String filePath) throws FileNotFoundException {
        in = new MyIn(filePath);
    }

    public void start() throws InterruptedException {
        while (in.hasNext()) {
            AimData aimData = new AimData(in.nextLine());
            pushData(DataStage.NEW, aimData);
        }
        notifyAll(DataStage.READY_FOR_SORT);
    }

    private ExecutorService service = Executors.newCachedThreadPool();

    public void notifyAll(DataStage stage) {
        if (routers.containsKey(stage)) {
            ArrayList<NotifyInterface> agents = routers.get(stage);
            for (NotifyInterface agent : agents) {
//                service.execute(agent::notice);
                agent.notice();
            }
        }
    }

    public AimData popData(DataStage stage) throws InterruptedException {
        return datas.get(stage).take();
    }

    public void pushData(DataStage dataStage, AimData aimData) throws InterruptedException {
        if (datas.containsKey(dataStage)) {
            datas.get(dataStage).put(aimData);
        } else {
            LinkedBlockingQueue<AimData> aimDatas = new LinkedBlockingQueue<>();
            aimDatas.put(aimData);
            datas.put(dataStage, aimDatas);
        }
        notifyAll(dataStage);
    }

    public ConcurrentHashMap<DataStage, LinkedBlockingQueue<AimData>> getDatas() {
        return datas;
    }

    @Override
    public boolean register(DataStage stage, NotifyInterface target) {
        ArrayList<NotifyInterface> handlerList;
        if (routers.containsKey(stage)) {
            handlerList = routers.get(stage);
        } else {
            handlerList = new ArrayList<>();
        }
        handlerList.add(target);
        routers.put(stage, handlerList);
        return true;
    }
}

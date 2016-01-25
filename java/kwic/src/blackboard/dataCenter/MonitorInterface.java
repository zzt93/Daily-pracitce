package blackboard.dataCenter;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public interface MonitorInterface {

    boolean register(DataStage stage, NotifyInterface target);
}

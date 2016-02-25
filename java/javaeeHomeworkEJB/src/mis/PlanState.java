package mis;

/**
 * Created by zzt on 2/25/16.
 * <p>
 * Usage:
 */
public enum PlanState {
    NEW("new, not viewed"),
    APPROVED("viewed by manager and approved"),
    REJECTED("viewed by manager and rejected")
    ;

    private final String des;

    PlanState(String s) {

        this.des = s;
    }
}

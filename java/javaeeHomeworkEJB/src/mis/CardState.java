package mis;

/**
 * Created by zzt on 2/19/16.
 * <p>
 * Usage:
 */
public enum CardState {
    LET_ACTIVATE("let to be activated"),
    ACTIVATED("activated"),
    SUSPEND("suspended"),
    CANCEL("canceled"),
    ;

    private final String des;

    CardState(String des) {
        this.des = des;
    }
}

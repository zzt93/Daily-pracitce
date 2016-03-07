package mis;

/**
 * Created by zzt on 3/7/16.
 * <p>
 * Usage:
 */
public enum  ReservationState {
    NOT_FINISHED(false, "Reservation"),
    FINISHED(true, "Sell")
    ;

    private final boolean state;
    private final String des;

    public boolean getState() {
        return state;
    }

    public String getDes() {
        return des;
    }

    ReservationState(boolean b, String s) {
        this.state = b;
        this.des = s;
    }
}

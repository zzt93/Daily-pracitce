package event;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class NoHandlerException extends Throwable {

    private final InputEvent cause;

    public NoHandlerException(InputEvent event) {
        cause = event;
    }

    public String getDescription() {
        return cause.getDescription();
    }
}

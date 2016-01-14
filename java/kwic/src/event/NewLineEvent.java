package event;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class NewLineEvent implements InputEvent {
    String line;

    public NewLineEvent(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }


    @Override
    public String getDescription() {
        return this.getClass().getName() + ":" + line;
    }

    @Override
    public String getInput() {
        return line;
    }
}

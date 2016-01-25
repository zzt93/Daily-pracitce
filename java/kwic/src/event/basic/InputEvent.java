package event.basic;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public abstract class InputEvent {
    private Input input;

    public String getDescription() {
        return this.getClass().getName() + ":" + input;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}

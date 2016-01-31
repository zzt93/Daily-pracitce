package event;

import event.basic.Input;
import event.basic.InputEvent;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class InputFinishedEvent extends InputEvent {
    private static Input input = new Input("");
    static {
        input.setInputs(null);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Input getInput() {
        return input;
    }
}

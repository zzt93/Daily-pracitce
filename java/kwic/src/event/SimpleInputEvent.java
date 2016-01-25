package event;

import event.basic.Input;
import event.basic.InputEvent;

import java.util.ArrayList;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class SimpleInputEvent extends InputEvent {

    public SimpleInputEvent(String line) {
        setInput(new Input(line));
    }

    public SimpleInputEvent(ArrayList<String> shift) {
        setInput(new Input(shift));
    }

}

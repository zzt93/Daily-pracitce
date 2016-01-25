package event;

import event.basic.Input;
import event.basic.InputEvent;

import java.util.ArrayList;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class ShiftedInputEvent extends InputEvent {

    public ShiftedInputEvent(ArrayList<String> shift) {
        setInput(new Input(shift));
    }

}

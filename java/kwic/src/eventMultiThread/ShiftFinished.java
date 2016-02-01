package eventMultiThread;

import event.basic.Input;
import event.basic.InputEvent;

/**
 * Created by zzt on 2/1/16.
 * <p>
 * Usage:
 */
public class ShiftFinished extends InputEvent {

    @Override
    public Input getInput() {
        return Input.finishedInput;
    }
}

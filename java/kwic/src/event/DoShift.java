package event;

import event.basic.*;
import pipeFilter.Shift;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class DoShift implements InputHandler {

    @Override
    public void receive(Input input) {
        for (String s : input.getInputs()) {
            try {
                EventRouter.throwEvent(new ShiftedInputEvent(Shift.shift(s)));
            } catch (NoHandlerException e) {
                e.printStackTrace();
            }
        }
    }
}

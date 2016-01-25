package event;

import event.basic.EventRouter;
import event.basic.Input;
import event.basic.InputHandler;
import event.basic.NoHandlerException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class DoSort implements InputHandler {
    private ArrayList<String> inputs = new ArrayList<>();

    @Override
    public void receive(Input input) {
        assert input == null;
        Collections.sort(inputs);
        try {
            EventRouter.throwEvent(new SortedEvent(new Input(inputs)));
        } catch (NoHandlerException e) {
            e.printStackTrace();
        }
    }

    class PrepareSort implements InputHandler {

        @Override
        public void receive(Input input) {
            inputs.addAll(input.getInputs());
        }
    }
}

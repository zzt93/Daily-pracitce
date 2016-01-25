package event;

import event.basic.EventRouter;
import event.basic.Input;
import event.basic.InputHandler;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class DoOutput implements InputHandler {

    public DoOutput() {
        EventRouter.register(SortedEvent.class, this);
    }

    @Override
    public void receive(Input input) {
        input.getInputs().forEach(System.out::println);
    }
}

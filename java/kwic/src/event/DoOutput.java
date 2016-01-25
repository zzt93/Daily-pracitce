package event;

import event.basic.Input;
import event.basic.InputHandler;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class DoOutput implements InputHandler {
    @Override
    public void receive(Input input) {
        input.getInputs().forEach(System.out::println);
    }
}

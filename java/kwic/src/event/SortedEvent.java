package event;

import event.basic.Input;
import event.basic.InputEvent;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class SortedEvent extends InputEvent {

    public SortedEvent(Input input) {
        setInput(input);
    }
}

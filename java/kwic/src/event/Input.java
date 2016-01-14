package event;

import java.util.ArrayList;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class Input {
    ArrayList<String> inputs = new ArrayList<>();


    public Input(String input) {
        inputs.add(input);
    }

    public void more(String string) {
        inputs.add(string);
    }
}

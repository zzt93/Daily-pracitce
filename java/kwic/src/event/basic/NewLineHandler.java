package event.basic;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class NewLineHandler implements InputHandler {
    Target target;

    public NewLineHandler(Target target) {
        this.target = target;
    }

    @Override
    public void sendInput(Input input) {
        target.receive(input);
    }
}

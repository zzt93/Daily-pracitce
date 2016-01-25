package event;

import event.basic.EventRouter;
import event.basic.NoHandlerException;
import utility.MyIn;

/**
 * Created by zzt on 1/24/16.
 * <p>
 * Usage:
 */
public class DoInput implements Runnable {

    MyIn in;

    public DoInput(MyIn in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (in.hasNextLine()) {
            try {
                EventRouter.throwEvent(new SimpleInputEvent(in.nextLine()));
            } catch (NoHandlerException e) {
                e.printStackTrace();
            }
        }
        try {
            EventRouter.throwEvent(new InputFinishedEvent());
        } catch (NoHandlerException e) {
            e.printStackTrace();
        }
    }
}

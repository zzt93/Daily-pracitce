package eventMultiThread;

import event.InputFinishedEvent;
import event.ShiftedInputEvent;
import event.SimpleInputEvent;
import event.basic.EventRouter;
import event.basic.Input;
import event.basic.NoHandlerException;
import eventMultiThread.basic.MultiThreadInputHandler;
import pipeFilter.Shift;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 1/31/16.
 * <p>
 * Usage:
 */
public class DoShift implements MultiThreadInputHandler {

    BlockingQueue<Input> queue = new ArrayBlockingQueue<>(100);

    public DoShift() {
        EventRouter.register(SimpleInputEvent.class, this);
        EventRouter.register(InputFinishedEvent.class, this);
    }

    @Override
    public void run() {
        while (true) {
            Input input;
            try {
                //can't use BlockingQueue#take() because it will hold the lock when waiting,
                //so  when the queue is empty, deadlock will happen
                input = queue.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
            if (input == null) {
                continue;
            }
            if (input.isFinished()) {
                try {
                    EventRouter.throwEvent(new ShiftFinished());
                } catch (NoHandlerException e) {
                    e.printStackTrace();
                }
                continue;
            }
            for (String s : input.getInputs()) {
                try {
                    EventRouter.throwEvent(new ShiftedInputEvent(Shift.shift(s)));
                } catch (NoHandlerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public BlockingQueue<Input> getQueue() {
        return queue;
    }
}

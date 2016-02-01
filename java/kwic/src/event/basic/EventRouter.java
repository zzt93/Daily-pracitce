package event.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class EventRouter {
    private static HashMap<Class<? extends InputEvent>, ArrayList<InputHandler>> routers = new HashMap<>();

    public static void throwEvent(InputEvent event) throws NoHandlerException {
        if (!routers.containsKey(event.getClass())) {
            throw new NoHandlerException(event);
        }
        ArrayList<InputHandler> eventHandlers = routers.get(event.getClass());
        if (eventHandlers.isEmpty()) {
            throw new NoHandlerException(event);
        }
        for (InputHandler eventHandler : eventHandlers) {
            // execute every handle in the different thread
            eventHandler.receive(event.getInput());
        }
    }

    public static void register(Class<? extends InputEvent> input, InputHandler handler) {
        ArrayList<InputHandler> handlerList;
        if (routers.containsKey(input)) {
            handlerList = routers.get(input);
        } else {
            handlerList = new ArrayList<>();
        }
        handlerList.add(handler);
        routers.put(input, handlerList);
    }
}

package event.basic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class EventRouter {
    private static HashMap<Class<? extends InputEvent>, ArrayList<InputHandler>> routers = new HashMap<>();

    public static void notify(InputEvent event) throws NoHandlerException {
        ArrayList<InputHandler> eventHandlers = routers.get(event.getClass());
        if (eventHandlers.isEmpty()) {
            throw new NoHandlerException(event);
        }
        for (InputHandler eventHandler : eventHandlers) {
            eventHandler.sendInput(new Input(event.getInput()));
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

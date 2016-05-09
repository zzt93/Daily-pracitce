package jcip.liveness;

import annotation.GuardedBy;
import annotation.ThreadSafe;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzt on 5/9/16.
 * <p>
 * Usage:
 */
public class OpenCall {
}

@ThreadSafe
class Taxi {
    @GuardedBy(type = Taxi.class, varName = "this")
    private Point now, des;

    private final Dispatcher dispatcher;

    Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void setReady(Point location) {
        boolean reachDes;
        synchronized (this) {
            now = location;
            reachDes = now.equals(des);
        }
        if (reachDes) {
            dispatcher.getImage();
        }
    }
}

@ThreadSafe
class Dispatcher {
    @GuardedBy(type = Dispatcher.class, varName = "this")
    private final Set<Taxi> all;


    @GuardedBy(type = Dispatcher.class, varName = "this")
    private final Set<Taxi> available;

    Dispatcher() {
        all = new HashSet<Taxi>();
        available = new HashSet<Taxi>();
    }

    public TaxiImage getImage() {
        Set<Taxi> copy;
        synchronized (this) {
            copy = new HashSet<>(all);
        }
        TaxiImage image = new TaxiImage();
        for (Taxi taxi : copy) {
            image.draw(taxi);
        }
        return image;
    }

    public synchronized void notifyReady(Taxi t) {
        available.add(t);
    }

    private static class TaxiImage {
        public void draw(Taxi taxi) {
        }
    }
}
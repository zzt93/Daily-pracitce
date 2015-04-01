package graph;

import java.util.Vector;

/**
 * Created by zzt on 3/26/15.
 */
public class Vertex<T> {
    Vector<Vertex> adj = new Vector<Vertex>();
    T core;

    public Vertex(T core) {
        this.core = core;
    }
}

package graph;

import java.util.Vector;

/**
 * Created by zzt on 3/26/15.
 */
public class Digraph {
    private Vector<Vertex> vertices;
    private Vector<Edge> edges;

    public Digraph() {
        edges = new Vector<Edge>();
        vertices = new Vector<Vertex>();
    }

    public Digraph(Digraph digraph) {
        this.vertices = digraph.getVertices();
        this.edges = digraph.getEdges();
    }

    public Vector<Vertex> getVertices() {
        return vertices;
    }

    public Vector<Edge> getEdges() {
        return edges;
    }

    /**
     * Test client
     * @param args
     */
    public static void main(String[] args) {

    }
}

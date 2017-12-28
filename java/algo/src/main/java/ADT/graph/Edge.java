package ADT.graph;

/**
 * Created by zzt on 3/26/15.
 */
public class Edge {
    double weight;
    Vertex next;

    public Edge(double weight, Vertex next) {
        this.weight = weight;
        this.next = next;
    }

    public Edge(Edge edge) {
        weight = edge.getWeight();
        next = edge.getNext();
    }

    public double getWeight() {
        return weight;
    }

    public Vertex getNext() {
        return next;
    }
}

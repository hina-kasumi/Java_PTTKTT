package org.hina.buoi8.Digraph;

public class DirectedEdge {
    private final int tail;
    private final int head;
    private final double weight;

    public DirectedEdge(int tail, int head, double weight) {
        if (tail < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (head < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.tail = tail;
        this.head = head;
        this.weight = weight;
    }

    public int from() {
        return head;
    }

    public int to() {
        return tail;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public String toString() {
        return head + "->" + tail + " " + String.format("%5.2f", weight);
    }
}

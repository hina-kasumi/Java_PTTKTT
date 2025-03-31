package org.hina.buoi9;

public class FlowEdge {
    private static final double FLOATING_POINT_EPSILON = 1E-10;
    private final int from; // from
    private final int to; // to
    private final double capacity; // capacity
    private double flow; // flow

    public FlowEdge(int from, int to, double capacity) {
        if (to < 0)
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (from < 0)
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (!(capacity >= 0.0))
            throw new IllegalArgumentException("Edge capacity must be non-negative");
        this.to = to;
        this.from = from;
        this.capacity = capacity;
        this.flow = 0;
    }

    public FlowEdge(int from, int to, double capacity, double flow) {
        if (to < 0)
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (from < 0)
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (!(capacity >= 0.0))
            throw new IllegalArgumentException("edge capacity must be non-negative");
        if (!(flow <= capacity))
            throw new IllegalArgumentException("flow exceeds capacity");
        if (!(flow >= 0.0))
            throw new IllegalArgumentException("flow must be non-negative");
        this.to = to;
        this.from = from;
        this.capacity = capacity;
        this.flow = flow;
    }

    public FlowEdge(FlowEdge edge) {
        this(edge.from, edge.to, edge.capacity, edge.flow);
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public int other(int vertex) {
        if (vertex == from)
            return to;
        if (vertex == to)
            return from;
        throw new IllegalArgumentException("invalid endpoint");
    }

    // Returns the residual capacity of the edge in the direction to the given {vertex}.
    public double residualCapacityTo(int vertex) {
        if (vertex == from)
            return flow; // backward edge
        else if (vertex == to)
            return capacity - flow; // forward edge
        else
            throw new IllegalArgumentException("invalid endpoint");
    }

    // Increases the flow on the edge in the direction to the given vertex.
    public void addResidualFlowTo(int vertex, double delta) {
        if (!(delta >= 0.0))
            throw new IllegalArgumentException("Delta must be nonnegative");

        if (vertex == from)
            flow -= delta; // backward edge
        else if (vertex == to)
            flow += delta; // forward edge
        else
            throw new IllegalArgumentException("invalid endpoint");

        if (Math.abs(flow) <= FLOATING_POINT_EPSILON)
            flow = 0;
        if (Math.abs(flow - capacity) <= FLOATING_POINT_EPSILON)
            flow = capacity;

        if (!(flow >= 0.0))
            throw new IllegalArgumentException("Flow is negative");
        if (!(flow <= capacity))
            throw new IllegalArgumentException("Flow exceeds capacity");
    }

    public String toString() {
        return from + "->" + to + " " + flow + "/" + capacity;
    }
}

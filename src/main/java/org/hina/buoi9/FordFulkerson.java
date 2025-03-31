package org.hina.buoi9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FordFulkerson {
    private static final double FLOATING_POINT_EPSILON = 1E-11;

    private final int V; // number of vertices
    private boolean[] marked; // marked[v] = true iff s->v path in residual graph
    private FlowEdge[] edgeTo; // edgeTo[v] = last edge on shortest residual s->v path
    private double value; // current value of max flow
    private final int s, t;
    private final FlowNetwork G;

    public FordFulkerson(FlowNetwork G, int s, int t) {
        this.G = G;
        V = G.V();
        validate(s);
        validate(t);
        this.t = t;
        this.s = s;
        if (s == t)
            throw new IllegalArgumentException("Source equals sink");
        if (!isFeasible(G, s, t))
            throw new IllegalArgumentException("Initial flow is infeasible");

        // while there exists an augmenting path, use it
        value = excess(G, t);

        while (hasAugmentingPath(G, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int i = t; i != s; i = edgeTo[i].other(i)) {
                bottle = Math.min(edgeTo[i].residualCapacityTo(i), bottle);
            }

            for (int i = t; i != s; i = edgeTo[i].other(i)) {
                // Bo sung vong for cho Duong tang luong o day ....
                edgeTo[i].addResidualFlowTo(i, bottle);
            }

            value += bottle;
        }
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v) {
        validate(v);
        return marked[v];
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        marked[s] = true;

        while (!queue.isEmpty() && !marked[t]) {
            int v = queue.poll();
            for (FlowEdge edge : G.adj(v)) {
                int w = edge.other(v);

                if (edge.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = edge;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }

        return marked[t];
    }

    private void validate(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // return excess flow at vertex v
    private boolean isFeasible(FlowNetwork G, int s, int t) {

        // check that capacity constraints are satisfied
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if (e.flow() < -FLOATING_POINT_EPSILON || e.flow() > e.capacity() + FLOATING_POINT_EPSILON) {
                    System.err.println("Edge does not satisfy capacity constraints: " + e);
                    return false;
                }
            }
        }

        // check that net flow into a vertex equals zero, except at source and sink
        if (Math.abs(value + excess(G, s)) > FLOATING_POINT_EPSILON) {
            System.err.println("Excess at source = " + excess(G, s));
            System.err.println("Max flow         = " + value);
            return false;
        }
        if (Math.abs(value - excess(G, t)) > FLOATING_POINT_EPSILON) {
            System.err.println("Excess at sink   = " + excess(G, t));
            System.err.println("Max flow         = " + value);
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s || v == t)
                continue;
            else if (Math.abs(excess(G, v)) > FLOATING_POINT_EPSILON) {
                System.err.println("Net flow out of " + v + " doesn't equal zero");
                return false;
            }
        }
        return true;
    }

    // return excess flow at vertex v
    private double excess(FlowNetwork G, int v) {
        double excess = 0.0;
        for (FlowEdge e : G.adj(v)) {
            if (v == e.from())
                excess -= e.flow();
            else
                excess += e.flow();
        }
        return excess;
    }

    public List<Integer> setA() {
        List<Integer> list = new ArrayList<>();
        boolean[] marked = new boolean[G.V()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        marked[s] = true;

        while (!queue.isEmpty() && !marked[t]) {
            int v = queue.poll();
            list.add(v);

            for (FlowEdge edge : G.adj(v)) {
                int w = edge.other(v);

                if (edge.residualCapacityTo(w) > 0 && !marked[w]) {
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }

        return list;
    }

    public double valMinCut() {
        List<Integer> list = setA();
        double val = 0;

        for (Integer i : list) {
            for (FlowEdge edge : G.adj(i)) {
                if (edge.from() == i && !list.contains(edge.to())) {
                    val += edge.residualCapacityTo(i);
                }
            }
        }

        return val;
    }

    public static void main(String[] args) {
        int v = 6;

        FlowNetwork flowNetwork = new FlowNetwork(v);
        flowNetwork.addEdge(new FlowEdge(0, 1, 10));
        flowNetwork.addEdge(new FlowEdge(0, 4, 10));
        flowNetwork.addEdge(new FlowEdge(4, 1, 2));
        flowNetwork.addEdge(new FlowEdge(1, 2, 9));
        flowNetwork.addEdge(new FlowEdge(4, 2, 8));
        flowNetwork.addEdge(new FlowEdge(4, 5, 4));
        flowNetwork.addEdge(new FlowEdge(2, 5, 6));
        flowNetwork.addEdge(new FlowEdge(5, 3, 10));
        flowNetwork.addEdge(new FlowEdge(2, 3, 10));

        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0, 3);

        System.out.println("max flow: " + fordFulkerson.value());

        System.out.print("tap A: ");
        fordFulkerson.setA().forEach((i) -> System.out.print(i + " "));

        System.out.println("\nmin cut: " + fordFulkerson.valMinCut());
    }
}

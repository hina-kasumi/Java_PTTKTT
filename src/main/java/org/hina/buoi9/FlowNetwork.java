package org.hina.buoi9;

import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private List<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<FlowEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Iterable<FlowEdge> edges() {
        List<FlowEdge> list = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (FlowEdge edge : adj(i)) {
                if (edge.to() != i) {
                    list.add(edge);
                }
            }
        }

        return list;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ":  ");
            for (FlowEdge e : adj[v]) {
                if (e.to() != v) s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

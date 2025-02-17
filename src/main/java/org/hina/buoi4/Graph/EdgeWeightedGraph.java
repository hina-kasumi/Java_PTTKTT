package org.hina.buoi4.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EdgeWeightedGraph {
    private final int v;
    private int e;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        if (v < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.v = v;
        this.e = 0;
        adj = new List[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public EdgeWeightedGraph(EdgeWeightedGraph g) {
        this(g.v);
        this.e = g.e;
        for (int i = 0; i < g.v; i++) {
            for (var e : g.adj[i]) {
                adj[i].add(e);
            }
        }
    }

    public int getE() {
        return e;
    }

    public int getV() {
        return v;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.v)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (v - 1));
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);

        validateVertex(v);
        validateVertex(w);

        adj[v].add(e);
        adj[w].add(e);
        this.e++;
    }

    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public Iterable<Edge> edges() {
        List<Edge> list = new ArrayList<>();
        for (int v = 0; v < this.v; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }

        return list;
    }
}

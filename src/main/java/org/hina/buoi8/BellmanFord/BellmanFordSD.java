package org.hina.buoi8.BellmanFord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.hina.buoi8.Digraph.*;

public class BellmanFordSD {
    private final double[] distFrom;               // distTo[v] = distance  of shortest s->v path
    private final DirectedEdge[] edgeFrom;         // edgeTo[v] = last edge on shortest s->v path
    private final boolean[] onQueue;             // onQueue[v] = is v currently on the queue?
    private final Queue<Integer> queue;          // queue of vertices to relax
    private int cost;                      // number of calls to relax()
    private Iterable<DirectedEdge> cycle;  // negative cycle (or null if no such cycle)

    public BellmanFordSD(ReverseEdgeWeightedDigraph digraph, int end) {
        distFrom = new double[digraph.getV()];
        edgeFrom = new DirectedEdge[digraph.getV()];
        onQueue = new boolean[digraph.getV()];
        for (int i = 0; i < digraph.getV(); i++) {
            distFrom[i] = Double.POSITIVE_INFINITY;
        }
        distFrom[end] = 0;
        queue = new LinkedList<>();
        queue.add(end);
        onQueue[end] = true;

        while (!queue.isEmpty()){
            int to = queue.poll();
            onQueue[to] = false;
            relax(digraph, to);
        }
    }

    private void relax(ReverseEdgeWeightedDigraph digraph, int to) {
        for(DirectedEdge edge : digraph.adj(to)) {
            int from = edge.from();
            if (distFrom[from] > distFrom[to] + edge.getWeight()) {
                distFrom[from] = distFrom[to] + edge.getWeight();
                edgeFrom[from] = edge;
                if (!onQueue[from]){
                    queue.add(from);
                    onQueue[from] = true;
                }
            }
            if (cost++ % digraph.getV() == 0){
                findNegativeCycle();
                if (hasNegativeCycle()) return;  // found a negative cycle
            }
        }
    }

    private void findNegativeCycle() {
        int v = edgeFrom.length;
        ReverseEdgeWeightedDigraph spt = new ReverseEdgeWeightedDigraph(v);
        for (int i = 0; i < v; i++) {
            if (edgeFrom[i] != null)
                spt.addEdge(edgeFrom[i]);
        }

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    private boolean hasNegativeCycle() {
        return cycle != null;
    }

    public double distFrom(int v){
        return distFrom[v];
    }

    public boolean hasPathFrom(int v) {
        return distFrom[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathFrom(int v) {
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathFrom(v)) return null;
        List<DirectedEdge> path = new ArrayList<>();
        for (DirectedEdge e = edgeFrom[v]; e != null; e = edgeFrom[e.to()]) {
            path.add(e);
        }

        return path;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/org/hina/buoi8/Digraph/file");
        Scanner scanner = new Scanner(file);
        ReverseEdgeWeightedDigraph digraph = new ReverseEdgeWeightedDigraph(scanner);

        BellmanFordSD bellmanFordSD = new BellmanFordSD(digraph, 6);
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " (" + bellmanFordSD.distFrom(i) + ")" + ": ");
            for (var x : bellmanFordSD.pathFrom(i))
                System.out.print(x + ", ");
            System.out.println();
        }
    }
}

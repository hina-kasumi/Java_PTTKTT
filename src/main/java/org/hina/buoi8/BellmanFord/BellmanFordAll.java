package org.hina.buoi8.BellmanFord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.hina.buoi8.Digraph.ReverseEdgeWeightedDigraph;
import org.hina.buoi8.Digraph.DirectedEdge;
import org.hina.buoi8.Digraph.EdgeWeightedDirectedCycle;

public class BellmanFordAll {
    private final double[][] distFrom; // distTo[v] = distance of shortest s->v path
    private final DirectedEdge[][] edgeFrom; // edgeTo[v] = last edge on shortest s->v path
    private final boolean[][] onQueue; // onQueue[v] = is v currently on the queue?
    private final Queue<Integer> queue; // queue of vertices to relax
    private int cost; // number of calls to relax()
    private Iterable<DirectedEdge> cycle; // negative cycle (or null if no such cycle)

    public BellmanFordAll(ReverseEdgeWeightedDigraph digraph) {
        distFrom = new double[digraph.getV()][digraph.getV()];
        edgeFrom = new DirectedEdge[digraph.getV()][digraph.getV()];
        onQueue = new boolean[digraph.getV()][digraph.getV()];
        for (int i = 0; i < digraph.getV(); i++) {
            Arrays.fill(distFrom[i], Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < distFrom.length; i++) {
            distFrom[i][i] = 0;
        }
        queue = new LinkedList<>();

        for (int i = 0; i < distFrom.length; i++) {
            queue.add(i);
            onQueue[i][i] = true;
            while (!queue.isEmpty()) {
                int to = queue.poll();
                onQueue[i][to] = false;
                relax(digraph, to, i);
            }
        }

    }

    private void relax(ReverseEdgeWeightedDigraph digraph, int to, int k) {
        for (DirectedEdge edge : digraph.adj(to)) {
            int from = edge.from();
            if (distFrom[k][from] > distFrom[k][to] + edge.getWeight()) {
                distFrom[k][from] = distFrom[k][to] + edge.getWeight();
                edgeFrom[k][from] = edge;
                if (!onQueue[k][from]) {
                    queue.add(from);
                    onQueue[k][from] = true;
                }
            }
            if (cost++ % digraph.getV() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle())
                    return; // found a negative cycle
            }
        }
    }

    private void findNegativeCycle() {
        int v = edgeFrom.length;
        ReverseEdgeWeightedDigraph spt = new ReverseEdgeWeightedDigraph(v);
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (edgeFrom[i][j] != null)
                    spt.addEdge(edgeFrom[i][j]);
            }

        }

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    private boolean hasNegativeCycle() {
        return cycle != null;
    }

    public double distFrom(int from, int to) {
        return distFrom[to][from];
    }

    public boolean hasPathFrom(int from, int to) {
        return distFrom[to][from] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathFrom(int from, int to) {
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathFrom(from, to))
            return null;
        List<DirectedEdge> path = new ArrayList<>();
        for (DirectedEdge e = edgeFrom[to][from]; e != null; e = edgeFrom[to][e.to()]) {
            path.add(e);
        }

        return path;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/org/hina/buoi8/Digraph/file");
        Scanner scanner = new Scanner(file);
        ReverseEdgeWeightedDigraph digraph = new ReverseEdgeWeightedDigraph(scanner);
        BellmanFordAll bellmanFordAll = new BellmanFordAll(digraph);

        for (int i = 0; i < 8; i++) {
            System.out.print(i + " (" + bellmanFordAll.distFrom(i, 6) + ")" + ": ");
            for (var x : bellmanFordAll.pathFrom(i, 6))
                System.out.print(x + ", ");
            System.out.println();
        }

    }
}

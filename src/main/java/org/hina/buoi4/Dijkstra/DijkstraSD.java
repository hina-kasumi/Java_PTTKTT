package org.hina.buoi4.Dijkstra;

import org.hina.Buoi3.IndexPriorityQueue.MinIndexPriorityQueue;
import org.hina.buoi4.Digraph.DirectedEdge;
import org.hina.buoi4.Digraph.ReverseEdgeWeightedDigraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DijkstraSD {
    private final double[] distTo; // khoảng cách ngắn nhất từ s tới v
    private final DirectedEdge[] edgeFrom; //edgeFrom[v] = first edge on shortest s->v path
    private final MinIndexPriorityQueue<Double> pq;

    public DijkstraSD(ReverseEdgeWeightedDigraph g, int end) {
        for (var e : g.edges()) {
            if (e.getWeight() < 0) throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        this.distTo = new double[g.getV()];
        this.edgeFrom = new DirectedEdge[g.getV()];
        validateVertex(end);

        // tạo gái trị ban đầu
        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[end] = 0;

        this.pq = new MinIndexPriorityQueue<>();
        this.pq.insert(end, distTo[end]);

        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (var e : g.adj(v))
                relax(e);
        }
    }

    private void relax(DirectedEdge e) {
        int from = e.from();
        int to = e.to();

        if (distTo[from] > distTo[to] + e.getWeight()) {
            distTo[from] = distTo[to] + e.getWeight();
            edgeFrom[from] = e;

            if (pq.contains(from))
                pq.updateKey(from, distTo[from]);
            else
                pq.insert(from, distTo[from]);
        }
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // trả về độ dài ngắn nhất từ điểm cho tới điểm v
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public List<DirectedEdge> pathFrom(int v) {
        List<DirectedEdge> path = new ArrayList<>();

        validateVertex(v);

        for (DirectedEdge e = edgeFrom[v]; e != null; e = edgeFrom[e.to()]) {
            path.add(e);
        }
        return path;
    }

    public boolean hasPathFrom(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("src/main/java/org/hina/buoi4/Dijkstra/DirectedGraphTestCase.txt");
        Scanner scanner = new Scanner(file);
        ReverseEdgeWeightedDigraph digraph = new ReverseEdgeWeightedDigraph(scanner);

        DijkstraSD dijkstraSD = new DijkstraSD(digraph, 5);

        dijkstraSD.pathFrom(0).forEach(System.out::println);
    }
}

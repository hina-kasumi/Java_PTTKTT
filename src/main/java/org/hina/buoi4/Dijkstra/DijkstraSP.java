package org.hina.buoi4.Dijkstra;

import org.hina.Buoi3.IndexPriorityQueue.MinIndexPriorityQueue;
import org.hina.buoi4.Digraph.DirectedEdge;
import org.hina.buoi4.Digraph.EdgeWeightedDigraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DijkstraSP {
    private final double[] distTo; // khoảng cách ngắn nhất từ s tới v
    private final DirectedEdge[] edgeTo; //edgeTo[v] = last edge on shortest s->v path
    private final MinIndexPriorityQueue<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph g, int start) {
        for (var e : g.edges()) {
            if (e.getWeight() < 0) throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[g.getV()];
        edgeTo = new DirectedEdge[g.getV()];
        validateVertex(start);

        // tạo gái trị ban đầu
        for (int i = 0; i < g.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[start] = 0;

        // khởi tạo Min Index PQ
        pq = new MinIndexPriorityQueue<>();
        pq.insert(start, distTo[start]);

        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (var e : g.adj(v))
                relax(e);
        }
    }

    private void relax(DirectedEdge e) {
        int from = e.from();
        int to = e.to();

        if (distTo[to] > distTo[from] + e.getWeight()) {
            distTo[to] = distTo[from] + e.getWeight();
            edgeTo[to] = e;

            if (pq.contains(to))
                pq.updateKey(to, distTo[to]);
            else
                pq.insert(to, distTo[to]);
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

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // in ra đường đi
    public List<DirectedEdge> pathTo(int v) {
        List<DirectedEdge> path = new ArrayList<>();

        validateVertex(v);

        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.add(e);
        }
        return path.reversed();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("src/main/java/org/hina/buoi4/Dijkstra/DirectedGraphTestCase.txt");
        Scanner scanner = new Scanner(file);
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(scanner);

        DijkstraSP dijkstraSP = new DijkstraSP(digraph, 0);

        dijkstraSP.pathTo(5).forEach(System.out::println);
        System.out.println(dijkstraSP.distTo(5));

    }
}

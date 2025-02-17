package org.hina.buoi4.Dijkstra;

import org.hina.Buoi3.IndexPriorityQueue.MinIndexPriorityQueue;
import org.hina.buoi4.Digraph.DirectedEdge;
import org.hina.buoi4.Digraph.EdgeWeightedDigraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DijkstraAll {
    private final double[][] distTo; // khoảng cách ngắn nhất từ s tới v
    private final DirectedEdge[][] edgeTo; //edgeTo[v] = last edge on shortest s->v path
    private final MinIndexPriorityQueue<Double> pq;

    public DijkstraAll(EdgeWeightedDigraph g) {
        for (var e : g.edges()) {
            if (e.getWeight() < 0) throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[g.getV()][g.getV()];
        edgeTo = new DirectedEdge[g.getV()][g.getV()];

        for (int i = 0; i < g.getV(); i++) {
            for (int j = 0; j < g.getV(); j++) {
                distTo[i][j] = Double.POSITIVE_INFINITY;
                if (i == j)
                    distTo[i][j] = 0;
            }
        }

        pq = new MinIndexPriorityQueue<>();
        for (int i = 0; i < g.getV(); i++) {
            pq.insert(i, distTo[i][i]);

            while (!pq.isEmpty()) {
                int v = pq.delMin();
                for (var e : g.adj(v))
                    relax(e, i);
            }
        }
    }

    private void relax(DirectedEdge e, int start) {
        int from = e.from();
        int to = e.to();

        if (distTo[start][to] > distTo[start][from] + e.getWeight()) {
            distTo[start][to] = distTo[start][from] + e.getWeight();
            edgeTo[start][to] = e;

            if (pq.contains(to))
                pq.updateKey(to, distTo[start][to]);
            else
                pq.insert(to, distTo[start][to]);
        }
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public double distTo(int start, int end) {
        validateVertex(start);
        validateVertex(end);
        return distTo[start][end];
    }

    public boolean hasPathTo(int start, int end) {
        validateVertex(start);
        validateVertex(end);
        return distTo[start][end] < Double.POSITIVE_INFINITY;
    }

    public List<DirectedEdge> pathTo(int start, int end) {
        List<DirectedEdge> path = new ArrayList<>();

        validateVertex(start);
        validateVertex(end);

        for (DirectedEdge e = edgeTo[start][end]; e != null; e = edgeTo[start][e.from()]) {
            path.add(e);
        }

        return path.reversed();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("src/main/java/org/hina/buoi4/Dijkstra/DirectedGraphTestCase.txt");
        Scanner scanner = new Scanner(file);
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(scanner);

        DijkstraAll dijkstraAll = new DijkstraAll(digraph);
        for (int i = 0; i < digraph.getV(); i++) {
            for (int j = 0; j < digraph.getV(); j++) {
                if (!dijkstraAll.hasPathTo(i, j))
                    System.out.println("không có đường đi từ " + i + " đến " + j);
                dijkstraAll.pathTo(i, j).forEach(System.out::println);
                System.out.println("----------");
            }
        }

        scanner.close();
    }
}

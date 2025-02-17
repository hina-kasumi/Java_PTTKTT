package org.hina.buoi4.MinimumSpanningTrees;

import org.hina.Buoi3.IndexPriorityQueue.MinIndexPriorityQueue;
import org.hina.buoi4.Graph.Edge;
import org.hina.buoi4.Graph.EdgeWeightedGraph;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Prim {
    private final Edge[] edgeTo;        // edgeTo[v] = Cạnh ngắn nhất từ đỉnh cây đến đỉnh không phải cây
    private final double[] distTo;      // distTo[v] = trọng số nhỏ nhất của các cạnh ngắn nhất
    private final boolean[] marked;     // marked[v] = đúng nếu nó thuộc cây
    private final MinIndexPriorityQueue<Double> pq;

    public Prim(EdgeWeightedGraph g) {
        edgeTo = new Edge[g.getV()];
        distTo = new double[g.getV()];
        marked = new boolean[g.getV()];

        pq = new MinIndexPriorityQueue<>();
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        for (int i = 0; i < g.getV(); i++) {
            if (!marked[i]) {
                prim(g, i);
            }
        }
    }

    //chạy thuật toán prim bắt đầu từ đỉnh s
    private void prim(EdgeWeightedGraph g, int s) {
        distTo[s] = 0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(g, v);
        }
    }

    private void scan(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (var e : g.adj(v)) {
            int w = e.other(v);

            if (marked[w])
                continue;
            if (e.getWeight() < distTo[w]) {
                distTo[w] = e.getWeight();
                edgeTo[w] = e;

                if (pq.contains(w))
                    pq.updateKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

    // trả về các cạnh trong cây khung nhỏ nhất
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new LinkedList<>();
        for (Edge e : edgeTo) {
            if (e != null) {
                mst.add(e);
            }
        }

        return mst;
    }

    // trả về tổng trọng số các cạnh trong cây khung nhỏ nhất
    public double weight() {
        double weight = 0;
        for (var e : edges()){
            weight += e.getWeight();
        }
        return weight;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("src/main/java/org/hina/buoi4/MinimumSpanningTrees/MST_TestCase.txt");
        Scanner scanner = new Scanner(file);

        int graphV = scanner.nextInt();
        int e = scanner.nextInt();

        EdgeWeightedGraph graph = new EdgeWeightedGraph(graphV);

        for (int i = 0; i < e; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            double weight = scanner.nextDouble();
            graph.addEdge(new Edge(v, w, weight));
        }

        Prim prim = new Prim(graph);

        prim.edges().forEach(System.out::println);
        System.out.println(prim.weight());

        scanner.close();
    }
}

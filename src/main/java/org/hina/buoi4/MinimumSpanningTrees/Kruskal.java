package org.hina.buoi4.MinimumSpanningTrees;

import org.hina.Buoi1.DisjointSetUnion;
import org.hina.buoi4.Graph.Edge;
import org.hina.buoi4.Graph.EdgeWeightedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Kruskal {
    private double weight;                        // tổng trọng số của cây khung
    private final Queue<Edge> mst = new LinkedList<>();  // các cạnh trong cây khung

    public Kruskal(EdgeWeightedGraph graph) {
        // create array of edges, sorted by weight
        Edge[] edges = new Edge[graph.getE()];
        int t = 0;
        for (var e : graph.edges()) {
            edges[t++] = e;
        }
        Arrays.sort(edges);

        DisjointSetUnion union = new DisjointSetUnion(graph.getV());
        for (int i = 0; i < graph.getE() && mst.size() < graph.getV() - 1; i++) {
            Edge edge = edges[i];
            int v = edge.either();
            int w = edge.other(v);

            // v và w không tạo thành một chu trình
            if (union.find(v) != union.find(w)) {
                union.union(v, w); // gộp v và w
                mst.add(edge);
                weight += edge.getWeight();
            }
        }
    }

    // trả về các cạnh trong cây khung nhỏ nhất
    public Iterable<Edge> edges() {
        return mst;
    }

    // trả về tổng trọng số các cạnh trong cây khung nhỏ nhất
    public double weight() {
        return weight;
    }


    public static void main(String[] args) throws FileNotFoundException {
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

        Kruskal kruskal = new Kruskal(graph);
        kruskal.edges().forEach(System.out::println);
        System.out.println(kruskal.weight());

        scanner.close();
    }
}

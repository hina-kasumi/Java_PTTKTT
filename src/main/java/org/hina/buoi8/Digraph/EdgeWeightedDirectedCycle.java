package org.hina.buoi8.Digraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EdgeWeightedDirectedCycle {
    private final ReverseEdgeWeightedDigraph digraph;
    private final DirectedEdge[] edgeFrom;
    private final int[] visitStatus;
    private boolean cycle;
    private DirectedEdge startCycleAt;

    public EdgeWeightedDirectedCycle(ReverseEdgeWeightedDigraph digraph) {
        this.digraph = digraph;
        edgeFrom = new DirectedEdge[digraph.getV()];
        visitStatus = new int[digraph.getV()];
        cycle = false;
        startCycleAt = null;
        for (int i = 0; i < digraph.getV(); i++) {
            if (visitStatus[i] == 0)
                dfs(digraph, i);
        }
    }

    public void dfs(ReverseEdgeWeightedDigraph digraph, int v) {
        visitStatus[v] = 1;
        for (DirectedEdge edge : digraph.adj(v)) {
            int from = edge.from();
            if (visitStatus[from] == 0) {
                dfs(digraph, from);
            } else if (visitStatus[from] == 1) {
                cycle = true;
                startCycleAt = edge;
                return;
            }
        }
        visitStatus[v] = 2;
    }

    public boolean hasCycle() {
        return cycle;
    }

    private void findCycle(List<DirectedEdge> list, DirectedEdge edge, boolean[] visited) {
        if (edge.from() == startCycleAt.to()) {
            list.add(edge);
            Arrays.fill(visited, true);
            System.out.println("return");
            return;
        }
        for (DirectedEdge directedEdge : digraph.adj(edge.from())) {
            int from = directedEdge.from();
            if (!visited[from]) {
                visited[from] = true;
                list.add(edge);
                findCycle(list, directedEdge, visited);
            }
        }
    }

    public Iterable<DirectedEdge> cycle() {
        if (!cycle)
            return null;
        List<DirectedEdge> list = new ArrayList<>();
        boolean[] visited = new boolean[digraph.getV()];
        findCycle(list, startCycleAt, visited);
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/org/hina/buoi8/Digraph/file");
        Scanner scanner = new Scanner(file);
        ReverseEdgeWeightedDigraph digraph = new ReverseEdgeWeightedDigraph(scanner);
        EdgeWeightedDirectedCycle cycle1 = new EdgeWeightedDirectedCycle(digraph);
        cycle1.cycle().forEach(System.out::println);
    }
}

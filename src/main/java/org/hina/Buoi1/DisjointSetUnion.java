package org.hina.Buoi1;

public class DisjointSetUnion {
    private final int n =(int) 100;
    private int[] parent = new int[n];
    private int[] size = new int[n];

    public void makeSet (){
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x){
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a != b){
            if (size[a] < size[b]){
                var temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
            size[a] += size[b];
        }
    }

    public static void main(String[] args) {
        DisjointSetUnion dsu = new DisjointSetUnion();
        dsu.makeSet();
    }
}

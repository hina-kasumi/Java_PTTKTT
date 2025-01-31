package org.hina.Buoi3.Transaction;

import java.util.PriorityQueue;
import java.util.Stack;

public class TopM {
    public static void main(String[] args) {
        Transaction[] a = new Transaction[]{
                new Transaction("Turing      6/17/1990   644.08"),
                new Transaction("vonNeumann  3/26/2002  4121.85"),
                new Transaction("Dijkstra    8/22/2007  2678.40"),
                new Transaction("vonNeumann  1/11/1999  4409.74"),
                new Transaction("Dijkstra   11/18/1995   837.42"),
                new Transaction("Hoare       5/10/1993  3229.27"),
                new Transaction("vonNeumann  2/12/1994  4732.35"),
                new Transaction("Hoare       8/18/1992  4381.21"),
                new Transaction("Turing      1/11/2002    66.10"),
                new Transaction("Thompson    2/27/2000  4747.08"),
                new Transaction("Turing      2/11/1991  2156.86"),
                new Transaction("Hoare       8/12/2003  1025.70"),
                new Transaction("vonNeumann 10/13/1993  2520.97"),
                new Transaction("Dijkstra    9/10/2000   708.95"),
                new Transaction("Turing     10/12/1993  3532.36"),
                new Transaction("Hoare       2/10/2005  4050.20")
        };

        PriorityQueue<Transaction> pq = new PriorityQueue<>();
        int m = 5;
        for(var x: a){
            pq.add(x);
            if (pq.size() > m)
                pq.poll();
        }

        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty())
            stack.add(pq.poll());
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
}

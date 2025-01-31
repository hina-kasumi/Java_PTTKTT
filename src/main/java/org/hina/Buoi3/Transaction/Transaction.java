package org.hina.Buoi3.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Transaction implements Comparable<Transaction> {
    private final String who;      // customer
    private final Date when;     // date
    private final double amount;   // amount

    public Transaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        who = a[0];
        when = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
    }

    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    @Override
    public int compareTo(Transaction o) {
        return Double.compare(this.amount, o.amount);
    }

    private static class WhoOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.who.compareTo(w.who);
        }
    }

    private static class WhenOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    private static class HowMuchOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return Double.compare(v.amount, w.amount);
        }
    }

    private static void print(Transaction[] a, String message, Comparator<Transaction> cmp) {
        System.out.println(message);
        Arrays.sort(a, cmp);
        new ArrayList<>(List.of(a)).forEach(System.out::println);
        System.out.println();
    }

    public static void main(String[] args) {
        Transaction[] a = new Transaction[]{
                new Transaction("Turing   6/17/1990  644.08"),
                new Transaction("Tarjan   3/26/2002 4121.85"),
                new Transaction("Knuth    6/14/1999  288.34"),
                new Transaction("Dijkstra 8/22/2007 2678.40")
        };

        print(a, "Sort by CompareTo", Comparator.naturalOrder());
        print(a, "Sort by date", new WhenOrder());
        print(a, "Sort by customer", new WhoOrder());
        print(a, "Sort by amount", new HowMuchOrder());
    }
}

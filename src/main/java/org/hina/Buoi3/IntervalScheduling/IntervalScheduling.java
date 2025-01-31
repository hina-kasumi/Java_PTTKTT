package org.hina.Buoi3.IntervalScheduling;

import java.util.PriorityQueue;

public class IntervalScheduling {
    private static class Activity implements Comparable<Activity> {
        int start;
        int finish;

        public Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "(" + start + " " + finish + ")";
        }

        @Override
        public int compareTo(Activity o) {
            return this.finish - o.finish;
        }
    }

    private static void printMaxActivities(int[] s, int[] f) {
        PriorityQueue<Activity> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < s.length; i++) {
            priorityQueue.add(new Activity(s[i], f[i]));
        }

        int i = 0;

        while (!priorityQueue.isEmpty()){
            if (priorityQueue.peek().start >= i) {
                System.out.println(priorityQueue.peek());
                assert priorityQueue.peek() != null;
                i = priorityQueue.peek().finish;
            }
            priorityQueue.poll();
        }
    }

    public static void main(String[] args) {
        int[] s = { 1, 3, 0, 5, 8, 5 };
        int[] f = { 2, 4, 6, 7, 9, 9 };

        printMaxActivities(s, f);
    }
}

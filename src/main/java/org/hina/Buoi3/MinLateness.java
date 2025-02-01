package org.hina.Buoi3;

import java.util.Arrays;

public class MinLateness {
    private record Job(int time, int date) implements Comparable<Job> {
        @Override
        public int compareTo(Job o) {
            return this.date - o.date;
        }
    }


    public static void main(String[] args) {
        Job[] jobs = new Job[]{
                new Job(3, 6),
                new Job(2, 8),
                new Job(1, 9),
                new Job(4, 9),
                new Job(3, 14),
                new Job(2, 15),
        };

        Arrays.sort(jobs);
        int late = 0, cur = 0;
        for (var job : jobs) {
            cur += job.time;
            late += Math.max(0, cur - job.date);
        }

        System.out.println(late);
    }
}

package org.hina.Buoi3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalPartitioning {
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
            if (this.start == o.start)
                return this.finish - o.finish;
            return this.start - o.start;
        }
    }

    private static int solve(int[] s, int[] f) {
        List<Activity> ls = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            ls.add(new Activity(s[i], f[i]));

        }
        ls.sort(Comparator.naturalOrder());

        int i = 0, j = 0;
        int cnt = 0;

        ls.forEach(System.out::println);

        while (i < ls.size()) {
            if (ls.get(i).start >= ls.get(j).finish) {
                j++;
            } else {
                cnt++;
            }
//            System.out.println(ls.get(i) + " " + ls.get(j));
            i++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] s = {0, 0, 0, 4, 4, 8, 8, 10, 12, 12};
        int[] f = {3, 7, 3, 7, 10, 11, 11, 15, 15, 15};

        System.out.println(solve(s, f));
    }
}

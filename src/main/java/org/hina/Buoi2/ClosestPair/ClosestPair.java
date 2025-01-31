package org.hina.Buoi2.ClosestPair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClosestPair {
    private static double brute(Point2D[] a, int left, int right) {
        double min = Double.MAX_VALUE;

        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double distance = a[i].distance(a[j]);
                if (min > distance) {
                    min = distance;
                }
            }
        }
        return min;
    }

    private static double solve(Point2D[] a, int left, int right) {
        if (right - left <= 3) {
            return brute(a, left, right);
        }

        int mid = (left + right) / 2;
        double min = solve(a, left, mid);
        min = Math.min(min, solve(a, mid, right));

//        min = Double.MAX_VALUE;

        List<Point2D> ls = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            var p = a[i];
            if (Math.abs(p.getX() - a[mid].getX()) < min)
                ls.add(p);
        }


        for (int i = 0; i < ls.size(); i++) {
            for (int j = i + 1; j < ls.size() && ls.get(i).getY() - ls.get(j).getY() < min; j++) {
                min = Math.min(min, ls.get(i).distance(ls.get(j)));
            }
        }

        return min;
    }

    private static double divide_and_conquer(Point2D[] a) {
        Arrays.sort(a, (a1, a2) -> {
            if (a1.getX() == a2.getY())
                return (int) (a1.getY() - a2.getY());
            return (int) (a1.getX() - a2.getX());
        });

        return solve(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        try (Scanner fileReader = new Scanner(new File("src/main/java/org/hina/Buoi2/ClosestPair/rs1423.txt"))) {
            int n = fileReader.nextInt();
            Point2D[] a = new Point2D[n];
            for (int i = 0; i < n; i++) {
                int x = fileReader.nextInt();
                int y = fileReader.nextInt();
                a[i] = new Point(x, y);
            }

            long cur = System.currentTimeMillis();
            System.out.println("duyệt trâu: " + brute(a, 0, n - 1));
            System.out.println(System.currentTimeMillis() - cur);

            long cur1 = System.currentTimeMillis();
            System.out.println("chia để trị: " + divide_and_conquer(a));
            System.out.println(System.currentTimeMillis() - cur1);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

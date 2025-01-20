package org.hina.Buoi2.Inversions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Inversions {


    //duyệt trâu
    private static int brute(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j])
                    cnt++;
            }
        }

        return cnt;
    }


    //chia để trị
    private static int countingInversions(int[] a, int left, int right) {
        if (left >= right)
            return 0;
        int inversion = 0;
        int mid = (left + right) / 2;
        inversion += countingInversions(a, left, mid);
        inversion += countingInversions(a, mid + 1, right);
        inversion += merge(a, left, mid, right);

        return inversion;
    }

    private static int merge(int[] a, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;

        int[] a1 = new int[n1];
        for (int i = 0; i < a1.length; i++) {
            a1[i] = a[i + left];
        }
        int[] a2 = new int[n2];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = a[mid + 1 + i];
        }

        int cnt = 0;

        int i = 0, j = 0;
        for (int k = left; k <= right; k++) {
            if (i < a1.length && j < a2.length) {
                if (a1[i] < a2[j])
                    a[k] = a1[i++];
                else {
                    a[k] = a2[j++];
                    cnt += n1 - i;
                }
            }
            else
                a[k] = (i >= a1.length) ? a2[j++] : a1[i++];
        }

        return cnt;
    }

    private static int solve(int[] a) {
        int[] b = a.clone();
        return countingInversions(b, 0, a.length - 1);
    }

    public static void main(String[] args) {
        try (Scanner fileReader = new Scanner(new File("src/main/java/org/hina/Buoi2/Inversions/TinyIn.txt"))) {
            int n = fileReader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < a.length; i++) {
                a[i] = fileReader.nextInt();
            }

            System.out.println("Dùng thuật toán chia để trị: " + solve(a));
            System.out.println("Duyệt trâu: " + brute(a));


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

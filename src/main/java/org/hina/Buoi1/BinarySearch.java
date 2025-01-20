package org.hina.Buoi1;

public class BinarySearch {
    private static int Search(int[] a, int target) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == target)
                return mid;
            else if (target < a[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    private static int lowerBound(int[] a, int target) {
        int left = 0, right = a.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target <= a[mid])
                right = mid;
            else {
                left = mid + 1;
            }
        }
        if (left < a.length && a[left] < target)
            left++;
        return left;
    }

    private static int upperBound (int[] a, int target) {
        int left = 0, right = a.length - 1;
        while (left < right){
            int mid = (left + right)/2;
            if (target >= a[mid])
                left = mid + 1;
            else
                right = mid;
        }

        if (left < a.length && a[left] <= target)
            left++;
        return left;
    }
}
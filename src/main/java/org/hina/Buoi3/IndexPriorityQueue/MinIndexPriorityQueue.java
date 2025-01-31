package org.hina.Buoi3.IndexPriorityQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinIndexPriorityQueue<T extends Comparable<T>> {
    private int maxN;        // Số lượng phần tử tối đa
    private int n;           // Số phần tử hiện tại
    private int[] pq;        // Heap nhị phân: pq[i] là index thực của phần tử i trong heap
    private int[] qp;        // Vị trí ngược: qp[index] cho biết index nằm ở đâu trong pq
    private T[] keys;        // keys[index] là giá trị ưu tiên của phần tử

    public MinIndexPriorityQueue() {
        this.maxN = 100;
        this.n = 0;
        keys = (T[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        Arrays.fill(qp, -1);
    }

    public int peek() {
        if (isEmpty())
            throw new NoSuchElementException("Hàng đợi rỗng!");
        return pq[1];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public void insert(int i, T key) {
        if (contains(i))
            throw new IllegalArgumentException("Index đã tồn tại");
        n++;
        pq[n] = i;
        qp[i] = n;
        keys[i] = key;
        swim(n);
    }

    public int delMin() {
        if (n == 0)
            throw new NoSuchElementException("Hàng đợi rỗng!");
        int minIndex = pq[1];
        exChange(1, n--);
        sink(1);
        qp[minIndex] = -1;
        keys[minIndex] = null;
        return minIndex;
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1))
                j++;
            if (!greater(k, j))
                break;
            exChange(k, j);
            k = j;
        }
    }

    public void updateKey(int i, T key) {
        if (!contains(i)) throw new NoSuchElementException("Index không tồn tại!");
        if (keys[i].compareTo(key) > 0) {
            keys[i] = key;
            swim(qp[i]);
        } else {
            keys[i] = key;
            sink(qp[i]);
        }
    }

    public void delete(int i) {
        if (isEmpty()) throw new NoSuchElementException("Hàng đợi rỗng!");
        if (!contains(i)) throw new NoSuchElementException("Index không tồn tại!");
        int pos = qp[i];
        exChange(pos, n--);
        swim(pos);
        sink(pos);
        keys[i] = null;
        qp[i] = -1;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exChange(k, k / 2);
            k /= 2;
        }
    }

    private void exChange(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public static void main(String[] args) {
        MinIndexPriorityQueue<Integer> pq = new MinIndexPriorityQueue<>();
        pq.insert(2, 30);
        pq.insert(5, 20);
        pq.insert(7, 10);
        pq.insert(3, 40);

        pq.updateKey(2, 50);

        while (!pq.isEmpty()) {
            System.out.println(pq.delMin());
        }

        System.out.println(pq.size());
    }
}

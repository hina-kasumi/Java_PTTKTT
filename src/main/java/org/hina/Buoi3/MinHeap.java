package org.hina.Buoi3;

public class MinHeap {
    private int MAX_SIZE = 100;
    private int[] arr = new int[MAX_SIZE + 1];
    private int size;

    public MinHeap() {
        size = 0;
    }

    private void swap(int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private boolean isEmpty() {
        return size <= 0;
    }

    private int peek() {
        if (isEmpty()) {
            System.err.println("Error: Heap is empty!!");
            return -1;
        }
        return arr[1];
    }

    private void add(int x) {
        arr[++size] = x;

        //heapify up
        int curIndex = size;
        int parentIndex = curIndex / 2;
        while (parentIndex != 0 && arr[parentIndex] > arr[curIndex]) {
            //doi cho
            swap(parentIndex, curIndex);
            curIndex = parentIndex;
            parentIndex = curIndex / 2;
        }
    }

    private int poll() {
        if (isEmpty()) {
            System.err.println("Error: Heap is empty!!");
            return -1;
        }
        int minRoot = arr[1];
        arr[1] = arr[size--];
        heapifyDown(1);

        return minRoot;
    }

    public void remove(int x) {
        int curIndex = -1;
        for (int i = 1; i <= size; i++) {
            if (arr[i] == x) {
                curIndex = i;
                break;
            }
        }

        if (curIndex == -1) {
            System.err.println("Error: Element is not found!");
            return;
        }

        arr[curIndex] = arr[size--];
        heapifyDown(curIndex);
    }

    private void heapifyDown(int currIndex) {
        //heapify down
        while ((currIndex * 2) <= size) {
            int leftChildIndex = 2 * currIndex;
            int rightChildIndex = leftChildIndex + 1;

            int smallerChildIndex = leftChildIndex;
            if (rightChildIndex <= size && arr[rightChildIndex] < arr[leftChildIndex]) {
                smallerChildIndex = rightChildIndex;
            }

            if (arr[currIndex] > arr[smallerChildIndex]) {
                swap(currIndex, smallerChildIndex);
                currIndex = smallerChildIndex;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        for (var x : new int[]{9, 7, 3, 6, 3, 63, 2})
            minHeap.add(x);

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
    }
}

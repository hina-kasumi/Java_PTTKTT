package org.hina.Buoi1;

public class Stack<T> {
    private static class Node<T> {
        T val;
        Node<T> next;

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node<T> head;
    private int size;

    private Stack() {
        size = 0;
        head = null;
    }

    private boolean isEmpty() {return size == 0;}
    private int getSize() {return size;}
    private T peek() {return head.val;}
    private void push(T val) {
        size++;
        if (size == 0) {
            head = new Node<>(val, null);
            return;
        }
        head = new Node<>(val, head);
    }

    private void poll() {
        if (size == 0)
            return;
        size--;
        head = head.next;
    }

    private void clear (){
        while (!isEmpty()){
            poll();
        }
    }
}
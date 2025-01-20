package org.hina.Buoi1;

public class Queue<T> {

    private static class Node<T>{
        private T val;
        private Node<T> next;

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }

    Node<T> head, tail;
    int size;

    public Queue() {
        size = 0;
        head = tail = null;
    }

    private int getSize(){return size;}
    private boolean isEmpty(){return size == 0;}
    private T front(){return head.val;}
    private T back () {return tail.val;}

    private void push (T val){
        if (size > 0) {
            tail = tail.next = new Node<>(val, null);
            size++;
        }
        else if (size == 0) {
            size++;
            head = tail = new Node<>(val, null);
        }
    }

    private void pop(){
        if (size == 1){
            head = tail = null;
            size--;
        }
        else if (size > 1) {
            head = head.next;
            size--;
        }
    }
}
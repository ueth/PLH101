package project2.utils;

import java.util.ArrayDeque;

public class BoundedQueue<E> {
    private ArrayDeque<E> queue;
    private int maxSize;

    public BoundedQueue(int maxSize) {
        this.queue = new ArrayDeque<E>();
        this.maxSize = maxSize;
    }

    public void push(E element) {
        if (queue.size() == maxSize) {
            queue.removeFirst();
        }
        queue.addLast(element);
    }

    public E pop() {
        return queue.removeFirst();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

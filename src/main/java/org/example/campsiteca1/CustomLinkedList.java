package org.example.campsiteca1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

public class CustomLinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<T> nodeAt(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + ", size " + size);
        }
    }

    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).data;
    }

    public int indexOf(T data) {
        int i = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (Objects.equals(current.data, data)) return i;
            i++;
        }
        return -1;
    }

    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    public T find(Predicate<T> test) {
        for (Node<T> current = head; current != null; current = current.next) {
            if (test.test(current.data)) return current.data;
        }
        return null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        if (tail == null) tail = newNode;   // list was empty, so the new node is also the last
        size++;
    }

    public void addAt(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + ", size " + size);
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            add(data);
            return;
        }

        Node<T> before = nodeAt(index - 1);
        Node<T> newNode = new Node<>(data);
        newNode.next = before.next;     // 1. new node points at what used to follow "before"
        before.next = newNode;          // 2. "before" now points at the new node
        size++;
    }

    public T set(int index, T data) {
        checkIndex(index);
        Node<T> node = nodeAt(index);
        T old = node.data;
        node.data = data;
        return old;
    }

    public boolean delete(T data) {
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, data)) {
                               return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void deleteAt(int index) {
        checkIndex(index);
        Node<T> previous = (index == 0) ? null : nodeAt(index - 1);
        Node<T> current = (previous == null) ? head : previous.next;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

      @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) throw new NoSuchElementException();
                T value = current.data;
                current = current.next;
                return value;
            }
        };
    }
}
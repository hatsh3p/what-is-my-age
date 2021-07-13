package com.hatsh3p;

public class LinkedList<T> implements List<T>{
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        this.size = 0;
    }

    @Override
    public void add(T item) {
        if (head == null) {
            head = new Node<>(item);
            tail = head;
        } else {
            Node<T> node = tail;
            tail.next = new Node<>(item);
            tail = tail.next;
        }
        ++size;
    }

    @Override
    public void add(int pos, T item) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();
        if (size() == 0 || pos == size() - 1) { // i.e. adding first element OR last element
            add(item);
        }
        if (pos == 0) {
            Node<T> node = new Node<>(item);
            node.next = head;
            head = node;
        } else {
            Node<T> previous = head;
            for (int i = 0; i < pos - 1; i++) {
                previous = previous.next;
            }
            Node<T> node = new Node<>(item);
            node.next = previous.next;
            previous.next = node;
        }
        ++size;
    }

    @Override
    public T get(int pos)  {
        if (pos < 0 || pos > size)
            throw new NullPointerException();
        Node<T> node = head;
        for (int i = 0; i < pos; i++) {
            node = node.next;
        }
        return node.data;
    }

    @Override
    public T remove(int pos)  {
        if (pos < 0 || pos > size)
            throw new NullPointerException();

        if (pos == 0) {
            Node<T> node = head;
            head = head.next;
            if (size() == 1) {
                head = tail = null;
            }
            --size;
            return node.data;
        } else {
            Node<T> previous = head;
            for (int i = 0; i < pos - 1; i++) {
                previous = previous.next;
            }
            Node<T> node = previous.next;
            previous.next = node.next;
            if (pos == size() - 1) {
                tail = node;
            }
            --size;
            return node.data;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> node = head;

        if (node == null) {
            return "NULL";
        }
        while (node != null) {
            stringBuilder.append(node.data).append("\n");
            node = node.next;
        }
        return stringBuilder.toString();
    }

    public Iterator<T> iterator() {
        return new ListIterator<>();
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> node = (Node<T>) head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            Node<T> previous = node;
            node = node.next;
            return previous.data;
        }
    }

}

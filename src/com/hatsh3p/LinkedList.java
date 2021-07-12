package com.hatsh3p;

public class LinkedList<T> implements List<T>{
    private int size;
    private Node<T> head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public void add(T item) {
        if (head == null) {
            head = new Node<T>(item);
        } else {
            Node<T> node = head;
            while (node.next != null) {
                node = node.next;
            }
            Node<T> newNode = new Node<T>(item);
            node.next = newNode;
        }
        ++size;
    }

    @Override
    public void add(int pos, T item) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();
        if (pos == 0) {
            Node<T> node = new Node<T>(item);
            node.next = head;
            head = node;
        } else {
            Node<T> previous = head;
            for (int i = 0; i < pos - 1; i++) {
                previous = previous.next;
            }
            Node<T> node = new Node<T>(item);
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
            --size;
            return node.data;
        } else {
            Node<T> previous = head;
            for (int i = 0; i < pos - 1; i++) {
                previous = previous.next;
            }
            Node<T> node = previous.next;
            previous.next = node.next;
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
            stringBuilder.append(node.data + "\n");
            node = node.next;
        }
        return stringBuilder.toString();
    }

    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> node = (Node<T>) head;

        @Override
        public boolean hasNext() {
            if (node != null)
                return true;
            else
                return false;
        }

        @Override
        public T next() {
            Node<T> previous = node;
            node = node.next;
            return previous.data;
        }
    }

}

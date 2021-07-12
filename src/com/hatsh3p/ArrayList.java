package com.hatsh3p;

public class ArrayList<T> implements List<T> {
    T[] arr;
    int size;

    public ArrayList() {
        size = 0;
        arr = (T[]) new Object[1000]; // ~ 5200 unique names in US in 2008
    }

    private void growArray() {
        T[] newArr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++)
            newArr[i] = arr[i];
        arr = newArr;
    }

    @Override
    public void add(T item) {
        if (size == arr.length)
            growArray();
        arr[size++] = item;
    }

    @Override
    public void add(int pos, T item)  {
        if (pos < 0 || pos > size - 1) //TODO: Check this
            throw new IndexOutOfBoundsException();
        if (size == arr.length)
            growArray();
        for (int i = size; i > pos; i--)
            arr[i] = arr[i - 1];
        arr[pos] = item;
        ++size;
    }

    @Override
    public T get(int pos) {
        if (pos < 0 || pos > size - 1)
            throw new IndexOutOfBoundsException();
        return arr[pos];
    }

    @Override
    public T remove(int pos) {
        if (pos < 0 || pos > size - 1)
            throw new IndexOutOfBoundsException();
        T item = arr[pos];
        for (int i = pos; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        --size;
        return item;
    }

    @Override
    public int size() {
        return size;
    }


//    public int search(T item) {
//        int start = 0;
//        int end = size;
//
//        while (start < end) {
//            int midpoint = (start + end) / 2;
//            if (arr[midpoint] == item) {
//                return midpoint;
//            } else if (arr[midpoint] < item) {
//                start = midpoint + 1;
//            } else {
//                end = midpoint;
//            }
//        }
//        return -1;
//    }

    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += arr[i] + "\n";
        }
        return result;
    }

    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

    // ListIterator is an inner class that has access to the data in ArrayList
    private class ListIterator<T> implements Iterator<T> {
        private int nextItem;

        public ListIterator() {
            nextItem = 0;
        }

        @Override
        public boolean hasNext() {
            return nextItem < size;
        }

        @Override
        public T next() {
            if (hasNext())
                return (T) arr[nextItem++];
            else
                return null;
        }
    }

}

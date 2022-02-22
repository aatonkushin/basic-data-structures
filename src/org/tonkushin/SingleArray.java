package org.tonkushin;

import java.util.Arrays;

public class SingleArray<T> implements IArray<T> {
    private T[] array;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public SingleArray() {
        array = (T[]) new Object[0];
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return array[index];
    }

    @Override
    public void add(T item) {
        if (size == this.array.length) {
            resize();
        }

        this.array[size] = item;
        size++;
    }

    @Override
    public void add(T item, int index) {
        checkBounds(index);

        if (size == this.array.length) {
            resize();
        }

        int length = size - index;
        if (length > 0) {
            System.arraycopy(array, index, array, index + 1, length);
        }

        array[index] = item;
        size++;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);

        T itemToRemove = array[index];

        int length = size - index - 1;
        if (length > 0) {
            System.arraycopy(array, index + 1, array, index, length);
            array[size - 1] = null;
        }

        size--;

        return itemToRemove;
    }

    private void resize() {
        array = Arrays.copyOf(array, size + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(";");
        }
        sb.append("]");
        return sb.toString();
    }


    @SuppressWarnings("unchecked")
    public void clear() {
        for (int i = 0; i < size; i++)
            array[i] = null;

        size=0;
        array = (T[]) new Object[0];
    }
}

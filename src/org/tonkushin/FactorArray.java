package org.tonkushin;

public class FactorArray<T> implements IArray<T> {
    private T[] array;
    int factor;
    int size = 0;

    @SuppressWarnings("unchecked")
    public FactorArray(int initialSize, int factor) {
        this.factor = factor;
        array = (T[]) new Object[initialSize];
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    @Override
    public void add(T item) {
        if (size() == array.length) {
            resize(factor);
        }

        array[size] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(T item, int index) {
        if (size() == array.length) {
            //  Если массив нужно расширить
            T[] newArray = (T[]) new Object[size() * factor + 1];

            if (!isEmpty()) {
                System.arraycopy(array, 0, newArray, 0, index);
            }

            // Устанавливаем элемент в указанном индексе
            newArray[index] = item;

            // Копируем от индекса
            System.arraycopy(array, index, newArray, index + 1, size() - index);

            array = newArray;
        } else {
            // в рамках существующего массива
            if (size - index >= 0) {
                System.arraycopy(array, index, array, index + 1, size - index);
            }

            array[index] = item;
        }

        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        T itemToRemove = array[index];

        int length = size - index - 1;
        if (length >= 0) {
            System.arraycopy(this.array, index + 1, this.array, index, length);
        }

        size--;

        this.array[size] = null;

        if (array.length > size() * factor) {
            resize(0);
        }

        return itemToRemove;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize(int f) {
        // При уменьшении размера контролируем новый размер массива
        // он не должен быть меньше размера данных
        int newSize = size() * f + 1;
        newSize = newSize > size() ? newSize : size();

        T[] newArray = (T[]) new Object[newSize];
        if (!isEmpty()) {
            System.arraycopy(array, 0, newArray, 0, size());
        }

        array = newArray;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        for (int i = 0; i < size(); i++)
            array[i] = null;

        array = (T[]) new Object[0];
        size = 0;
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
}

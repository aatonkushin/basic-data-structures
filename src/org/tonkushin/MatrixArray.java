package org.tonkushin;

public class MatrixArray<T> implements IArray<T> {
    private SingleArray<IArray<T>> array;
    private final int defaultVectorArraySize = 9800;

    public MatrixArray() {
        this.array = new SingleArray<>();
        this.array.add(new VectorArray<>(defaultVectorArraySize));
    }

    @Override
    public T get(int index) {
        int pos = 0;    // начальная позиция для каждого массива

        for (int i = 0; i < this.array.size(); i++) {
            if (index >= pos && index < pos + this.array.get(i).size()) {
                int internalIndex = index - pos;
                return this.array.get(i).get(internalIndex);
            }

            pos += this.array.get(i).size();
        }

        throw new RuntimeException(String.format("Item with index %d not found", index));
    }

    @Override
    public void add(T item) {
        if (this.array.isEmpty()) {
            this.array.add(new VectorArray<>(defaultVectorArraySize));
        }

        // Добавляем запись в последний массив
        if (this.array.get(this.array.size() - 1).size() < defaultVectorArraySize) {
            this.array.get(this.array.size() - 1).add(item);
            return;
        }

        // Если места не нашлось, то добавляем новый массив
        IArray<T> arr = new VectorArray<>(defaultVectorArraySize);
        arr.add(item);
        this.array.add(arr);
    }

    @Override
    public void add(T item, int index) {
//        if (index < 0 || index > this.size()) {
//            throw new IndexOutOfBoundsException(index);
//        }
//
//        if (this.array.isEmpty()) {
//            this.array.add(new VectorArray<T>(defaultVectorArraySize));
//        }
//
//        int pos = 0;    // начальная позиция для каждого массива
//
//        for (int i = 0; i < this.array.size(); i++) {
//            if (index >= pos && index <= pos + this.array.get(i).size()) {
//                int internalIndex = index - pos;
//                this.array.get(i).add(item, internalIndex);
//                return;
//            }
//
//            pos += this.array.get(i).size();
//        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException(index);
        }

        int pos = 0;    // начальная позиция для каждого массива

        for (int i = 0; i < this.array.size(); i++) {
            if (index >= pos && index < pos + this.array.get(i).size()) {
                int internalIndex = index - pos;
                T item = this.array.get(i).remove(internalIndex);

                // Удаляем пустой массив
                if (this.array.get(i).isEmpty()) {
                    this.array.remove(i);
                }

                return item;
            }

            pos += this.array.get(i).size();
        }

        throw new RuntimeException(String.format("Item with index %d not found", index));
    }

    @Override
    public int size() {
        int totalSize = 0;
        for (int i = 0; i < this.array.size(); i++) {
            totalSize += this.array.get(i).size();
        }

        return totalSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.array.size(); i++) {
            for (int j = 0; j < this.array.get(i).size(); j++) {
                sb.append(this.array.get(i).get(j)).append("; ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void clear() {
        this.array = new SingleArray<>();
    }
}

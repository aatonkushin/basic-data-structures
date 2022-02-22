package org.tonkushin;

import java.util.ArrayList;
import java.util.List;

public class JavaArrayList<T> implements IArray<T> {
    List<T> arrayList = new ArrayList<>();

    @Override
    public T get(int index) {
        return arrayList.get(index);
    }

    @Override
    public void add(T item) {
        arrayList.add(item);
    }

    @Override
    public void add(T item, int index) {
        arrayList.add(index, item);
    }

    @Override
    public T remove(int index) {
        return arrayList.remove(index);
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

}

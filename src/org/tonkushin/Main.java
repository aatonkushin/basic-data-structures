package org.tonkushin;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int min = 1_000;
        int max = 10_000_000;

//        int min = 10;
//        int max = 10;

        IArray<Integer> array;

//        System.out.println("Test SingleArray:");
//        array = new SingleArray<>();
//        testArray(array, min, max);

//        System.out.println("Test VectorArray:");
//        array = new VectorArray<>(1000);
//        testArray(array, min, max);
//
//        System.out.println("Test FactorArray:");
//        array = new FactorArray<>(9800, 2);
//        testArray(array, min, max);
//
//        System.out.println("Test MatrixArray:");
//        array = new MatrixArray<>();
//        testArray(array, min, max);
//
        System.out.println("Test JavaArrayList:");
        array = new JavaArrayList<>();
        testArray(array, min, max);
    }

    private static void testArray(IArray<Integer> array, int min, int max) {
        for (int i = min; i <= max; i *= 10) {

            // Добавление элемента в конец массива
            testAdd(array, i);
//            System.out.println(array);

            // Удаление элемента из списка
            testRemove(array, i);
//            System.out.println(array);

            // Вставка элемента в начало массива
            array.clear();
            testInsert(array, i);
//            System.out.println(array);

            // Вставка элемента в случайное место массива
            array.clear();
            testInsertRandom(array, i);
//            System.out.println(array);

            System.out.println("=== === === ===");
        }
    }

    private static void testAdd(IArray<Integer> array, int total) {
        Stopwatch sw = new Stopwatch();
        sw.start();

        for (int i = 0; i < total; i++) {
            array.add(i);
        }

        sw.stop();

        System.out.println("add(item): " + total + " " + sw.getElapsedTimeString());
    }

    private static void testInsert(IArray<Integer> array, int total) {
        Stopwatch sw = new Stopwatch();
        sw.start();

        for (int i = 0; i < total; i++) {
            array.add(i, 0);
        }

        sw.stop();

        System.out.println("add(0, item): " + total + ", " + sw.getElapsedTimeString());
    }

    private static void testInsertRandom(IArray<Integer> array, int total) {
        Stopwatch sw = new Stopwatch();
        sw.start();

        for (int i = 0; i < total; i++) {
            array.add(i, getRandom(0, array.size() >= 1 ? array.size() - 1 : 0));
        }

        sw.stop();

        System.out.println("add(randomIndex, item): " + total + ", " + sw.getElapsedTimeString());
    }

    private static void testRemove(IArray<Integer> array, int total) {
        Stopwatch sw = new Stopwatch();
        sw.start();

        for (int i = 0; i < total; i++) {
            array.remove(0);
        }

        sw.stop();

        System.out.println("remove(0): " + total + ", " + sw.getElapsedTimeString());
    }

    private static int getRandom(int min, int max) {
        if (min == 0 && max == 0) {
            return 0;
        }

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
